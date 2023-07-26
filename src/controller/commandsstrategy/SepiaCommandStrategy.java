package controller.commandsstrategy;

import model.ImageDatabaseInterface;
import model.image.CustomImageState;
import model.operations.GreyscaleOperation;
import model.operations.SepiaOperation;

/**
 * This class is the strategy for the Sepia command.
 * @see CommandStrategyInterface
 */
public class SepiaCommandStrategy implements CommandStrategyInterface {

  @Override
  public void run(String[] commandsList, ImageDatabaseInterface imageDatabase) {
    // 0. Validate all the arguments.
    if (commandsList.length < 3) {
      throw new IllegalStateException("Too few arguments. Must be of the format: \n"
              + " \"sepia sourceImageID newImageID\"");
    }

    // 1. Set the sourceImageID and the destinationID.
    String sourceImageID = commandsList[1];
    String destinationID = commandsList[2];

    // 2. Once all the arguments are validated, call the greyscale method.
    CustomImageState newImage = new SepiaOperation(imageDatabase.getImage(sourceImageID))
            .applyOperation();

    // 3. Add the new image to the imageDatabase using the destinationID.
    imageDatabase.addImage(destinationID, newImage);
  }
}
