package controller.commandsstrategy.operations.colortransformation;

import controller.commandsstrategy.CommandStrategyInterface;
import model.ImageDatabaseInterface;
import model.image.CustomImageState;
import model.operations.OperationInterface;

/**
 * This class is responsible for running the color command.
 * It then looks for `operation` and then calls the factory to create the filter.
 */
public class ColorCommandStrategy implements CommandStrategyInterface {

  @Override
  public void run(String[] commandsList, ImageDatabaseInterface imageDatabase) {
    // 0. Validate all the arguments.
    if (commandsList.length < 3) {
      throw new IllegalStateException("Must be of the format: \n"
              + " \"color <operation> <sourceImageID> <newImageID>\"");
    }

    // 1. Set the sourceImageID and the destinationID.
    String operation = commandsList[1];
    String sourceImageID = commandsList[2];
    String destinationID = commandsList[3];

    // 2. Check the operation and apply the filter.
    // SOLID : using Open-Closed principle here.
    //         We can add new filters without changing the code.
    //         only modify the FilterFactory class.
    CustomImageState newImage;
    OperationInterface filter = ColorFactory.createFilter(operation, sourceImageID, imageDatabase);
    newImage = filter.applyOperation();

    // 3. Add the new image to the imageDatabase using the destinationID.
    imageDatabase.addImage(destinationID, newImage);
  }
}
