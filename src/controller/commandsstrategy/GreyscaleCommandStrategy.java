package controller.commandsstrategy;

import model.ImageDatabaseInterface;
import model.image.CustomImageState;
import model.operations.Filters.GreyscaleFilter;

public class GreyscaleCommandStrategy implements CommandStrategyInterface {

  @Override
  public void run(String[] commandsList, ImageDatabaseInterface imageDatabase) {
    // 0. Validate all the arguments.
    if (commandsList.length < 3) {
      throw new IllegalStateException("Too few arguments. Must be of the format: \n"
              + " \"greyscale sourceImageID newImageID\"");
    }

    // 1. Set the sourceImageID and the destinationID.
    String sourceImageID = commandsList[1];
    String destinationID = commandsList[2];

    // 2. Once all the arguments are validated, call the greyscale method.
    CustomImageState newImage = new GreyscaleFilter(imageDatabase.getImage(sourceImageID))
            .applyOperation();

    // 3. Add the new image to the imageDatabase using the destinationID.
    imageDatabase.addImage(destinationID, newImage);
  }
}