/*
 * This file is generated. Do not change it manually.
 */

#ifndef DE_UNISTUTTGART_KARA_COMMANDSINITKARA_H
#define DE_UNISTUTTGART_KARA_COMMANDSINITKARA_H

#include "CompositeCommandBase.h"
#include "InitKaraCommandParameters.h"

#include "Location.h"
#include "Direction.h"
#include "ConcreteKara.h"
#include "Tile.h"
#include "ConcreteWorld.h"
#include "TileContent.h"
#include "Stage.h"
#include "Size.h"

namespace commands {

/**
 * Initialize a kara object with the given parameters. Once a kara is initialized, the method must not be called again.<br/>
 * <br/>
 * <b>requires / ensures:</b> the world tiles must not be empty<br/>
 * <b>requires:</b> kara must not be already initialized<br/>
 * <b>ensures:</b> Kara has to be placed on the tile's location<br/>
 * <b>requires:</b> location has to be on the world
 * 
 */

class InitKaraCommand final : public CompositeCommandBase {
private:
	std::shared_ptr<kara::ConcreteWorld> self;
	commands::CommandStack &commandStack;

	mpw::Location location;

	mpw::Direction direction;

public:

	InitKaraCommand(InitKaraCommandParameters parameters) : self(
			parameters.self), commandStack(parameters.commandStack), location(
			parameters.location), direction(parameters.direction)

	{
	}

	void execute() override;

private:

	bool internalMainUnit();

	bool setProperties(mpw::Direction initialDirection);

	bool addToWorld(mpw::Location location);

	std::shared_ptr<mpw::Tile> addToWorld_findO0(kara::EditorWorld &source);

};

}

#endif //DE_UNISTUTTGART_KARA_COMMANDSINITKARA_H

