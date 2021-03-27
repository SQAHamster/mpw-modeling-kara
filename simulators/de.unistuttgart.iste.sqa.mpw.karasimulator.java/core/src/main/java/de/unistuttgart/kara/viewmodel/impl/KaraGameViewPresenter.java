package de.unistuttgart.kara.viewmodel.impl;

import de.unistuttgart.iste.sqa.mpw.framework.mpw.LogEntry;
import de.unistuttgart.iste.sqa.mpw.framework.datatypes.Size;
import de.unistuttgart.iste.sqa.mpw.framework.mpw.Tile;
import de.unistuttgart.iste.sqa.mpw.framework.viewmodel.ViewModelCell;
import de.unistuttgart.iste.sqa.mpw.framework.viewmodel.ViewModelCellLayer;
import de.unistuttgart.iste.sqa.mpw.framework.viewmodel.ViewModelLogEntry;
import de.unistuttgart.iste.sqa.mpw.framework.viewmodel.impl.GameViewPresenterBase;
import de.unistuttgart.kara.kara.*;
import de.unistuttgart.kara.facade.*;
import javafx.beans.property.ReadOnlyListProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class KaraGameViewPresenter extends GameViewPresenterBase {
	private final World world;

	private final Map<LogEntry, ViewModelLogEntry> logEntryMap = new HashMap<>();

	public KaraGameViewPresenter(KaraGame game) {
		super(game);
		this.world = game.getWorld();
	}

	@Override
	protected Size getStageSizeFromConcreteStage() {
		return world.getWorldSize();
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

		kara.directionProperty().addListener((v, c, l) -> {
			runLocked(() -> {
				refreshKaraLayer(layer, kara);
			});
		});

		refreshKaraLayer(layer, kara);
		cell.getLayers().add(layer);
	}

	private void refreshKaraLayer(ViewModelCellLayer layer, ReadOnlyKara kara) {
		layer.setVisible(kara.getCurrentTile() != null);
		layer.setRotation(getRotationForDirection(kara.getDirection()));
	}

}
