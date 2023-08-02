package controller.commandsstrategy.operations.greyscale;

import controller.commandsstrategy.CommandStrategyInterface;
import model.ImageDatabaseInterface;
import model.image.CustomImageState;
import model.operations.OperationInterface;

/**
 * This class is responsible for running the greyscale command.
 * It then looks for `operation` and then calls the factory to create the filter.
 */
public class GreyscaleCommandStrategy implements CommandStrategyInterface {

  /**
   * This method runs the greyscale command.
   * It then looks for `operation` and then calls the factory to create the filter.
   *
   * @param commandsList the list of commands.
   * @param imageDatabase the image database.
   */
  @Override
  public void run(String[] commandsList, ImageDatabaseInterface imageDatabase) {
    // 0. Validate all the arguments.
    if (commandsList.length < 4) {
      throw new IllegalStateException("Too few arguments. Must be of the format: \n"
              + " \"greyscale <operation/color> <sourceImageID> <newImageID>\"");
    }

    // 1. Set the sourceImageID and the destinationID.
    String operation = commandsList[1];
    String sourceImageID = commandsList[2];
    String destinationID = commandsList[3];

    // 2. Check the operation and apply the filter.
    // SOLID : using Open-Closed principle here.
    //         We can add new filters without changing the code.
    //         only modify the GreyscaleFactory class.
    CustomImageState newImage;
    OperationInterface filter = GreyscaleFactory.
            createFilter(operation, sourceImageID, imageDatabase);
    newImage = filter.applyOperation();

    // 3. Add the new image to the imageDatabase using the destinationID.
    imageDatabase.addImage(destinationID, newImage);
  }
}
