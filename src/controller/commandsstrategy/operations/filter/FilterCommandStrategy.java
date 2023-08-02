package controller.commandsstrategy.operations.filter;

import controller.commandsstrategy.CommandStrategyInterface;
import model.ImageDatabaseInterface;
import model.image.CustomImageState;
import model.operations.OperationInterface;

/**
 * This class is the strategy for the Sepia command.
 * @see CommandStrategyInterface
 */
public class FilterCommandStrategy implements CommandStrategyInterface {

  @Override
  public void run(String[] commandsList, ImageDatabaseInterface imageDatabase) {
    // 0. Validate all the arguments.
    if (commandsList.length < 3) {
      throw new IllegalStateException("Must be of the format: \n"
              + " \"filter <operation> <sourceImageID> <newImageID>\"");
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
    OperationInterface filter = FilterFactory.createFilter(operation, sourceImageID, imageDatabase);
    newImage = filter.applyOperation();

    // 3. Add the new image to the imageDatabase using the destinationID.
    imageDatabase.addImage(destinationID, newImage);
  }
}
