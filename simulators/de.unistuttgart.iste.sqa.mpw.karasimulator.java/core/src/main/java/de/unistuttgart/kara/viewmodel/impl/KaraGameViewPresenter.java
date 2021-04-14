package de.unistuttgart.kara.viewmodel.impl;

import de.unistuttgart.iste.sqa.mpw.framework.datatypes.Direction;
import de.unistuttgart.iste.sqa.mpw.framework.datatypes.Size;
import de.unistuttgart.iste.sqa.mpw.framework.mpw.Tile;
import de.unistuttgart.iste.sqa.mpw.framework.viewmodel.ViewModelCell;
import de.unistuttgart.iste.sqa.mpw.framework.viewmodel.ViewModelCellLayer;
import de.unistuttgart.iste.sqa.mpw.framework.viewmodel.impl.GameViewPresenterBase;
import de.unistuttgart.kara.facade.KaraGame;
import de.unistuttgart.kara.facade.World;
import de.unistuttgart.kara.kara.Leaf;
import de.unistuttgart.kara.kara.Mushroom;
import de.unistuttgart.kara.kara.ReadOnlyKara;
import de.unistuttgart.kara.kara.Tree;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;

import java.util.Optional;

public class KaraGameViewPresenter extends GameViewPresenterBase {
	private final World world;
	private ChangeListener<Direction> karaDirectionChangeListener;

	public KaraGameViewPresenter(KaraGame game) {
		super(game);
		this.world = game.getWorld();
	}

	@Override
	protected ReadOnlyObjectProperty<Size> getStageSizeFromConcreteStage() {
		return world.getInternalWorld().stageSizeProperty();
	}

	@Override
	protected ReadOnlyListProperty<Tile> getTilesPropertyFromConcreteStage() {
		return world.getInternalWorld().tilesProperty();
	}

	@Override
	protected void onSetTileNodeAtForCell(ViewModelCell cell, Tile tile) {
		configureTreeImageView(cell, tile);
		configureLeafImageView(cell, tile);
		configureMushroomImageView(cell, tile);

		Optional<ReadOnlyKara> karaOptional = findKaraOnTile(tile);
		karaOptional.ifPresent(readOnlyKara -> configureKaraImageView(cell, readOnlyKara));
	}

	private Optional<ReadOnlyKara> findKaraOnTile(Tile tile) {
		return tile.getContents().stream()
				.filter(ReadOnlyKara.class::isInstance)
				.map(ReadOnlyKara.class::cast).findFirst();
	}

	private void configureTreeImageView(ViewModelCell cell, Tile tile) {
		var layer = new ViewModelCellLayer();
		layer.setImageName("Tree");
		refreshTreeLayer(layer, tile);
		cell.getLayers().add(layer);
	}

	private void refreshTreeLayer(ViewModelCellLayer layer, Tile tile) {
		layer.setVisible(tile.getContents().stream().anyMatch(Tree.class::isInstance));
	}

	private void configureLeafImageView(ViewModelCell cell, Tile tile) {
		var layer = new ViewModelCellLayer();
		layer.setImageName("Leaf");
		refreshLeafLayer(layer, tile);
		cell.getLayers().add(layer);
	}

	private void refreshLeafLayer(ViewModelCellLayer layer, Tile tile) {
		layer.setVisible(tile.getContents().stream().anyMatch(Leaf.class::isInstance));
	}

	private void configureMushroomImageView(ViewModelCell cell, Tile tile) {
		var layer = new ViewModelCellLayer();
		layer.setImageName("Mushroom");
		refreshMushroomLayer(layer, tile);
		cell.getLayers().add(layer);
	}

	private void refreshMushroomLayer(ViewModelCellLayer layer, Tile tile) {
		layer.setVisible(tile.getContents().stream().anyMatch(Mushroom.class::isInstance));
	}

	private void configureKaraImageView(ViewModelCell cell, ReadOnlyKara kara) {
		var layer = new ViewModelCellLayer();
		layer.setImageName("Kara");

		addKaraDirectionListener(layer, kara);

		refreshKaraLayer(layer, kara);
		cell.getLayers().add(layer);
	}

	/*
	 * Adds a listener for the change of the direction, to also update the layers if Kara turns left.
	 * Note: Since onSetTileNodeAtForCell() is called every time the contents of a tile changes, Kara might
	 * be configured multiple times. Avoid, that multiple direction listeners are attached.
	 */
	private void addKaraDirectionListener(final ViewModelCellLayer layer, final ReadOnlyKara kara) {
		if (karaDirectionChangeListener != null) {
			kara.directionProperty().removeListener(karaDirectionChangeListener);
		}
		karaDirectionChangeListener = (property, oldValue, newValue) -> {
			runLocked(() -> {
				refreshKaraLayer(layer, kara);
			});
		};
		kara.directionProperty().addListener(karaDirectionChangeListener);
	}

	private void refreshKaraLayer(ViewModelCellLayer layer, ReadOnlyKara kara) {
		layer.setVisible(kara.getCurrentTile() != null);
		if (kara.getDirection() != null) {
			layer.setRotation(getRotationForDirection(kara.getDirection()));
		}
	}

}
