package controller.commandsstrategy.operations.greyscale;

import model.ImageDatabaseInterface;
import model.operations.OperationInterface;
import model.operations.greyscale.ColorComponentOperation;
import model.operations.greyscale.IntensityOperation;
import model.operations.greyscale.LumaOperation;
import model.operations.greyscale.ValueOperation;

/**
 * This class is a factory for creating greyscale filters.
 * It implements the Factory design pattern.
 */
public class GreyscaleFactory {

  /**
   * This method produces a greyscale filter based on the operation.
   *
   * @param operation the operation to be performed.
   * @param sourceImageID the source image ID.
   * @param imageDatabase the image database.
   * @return the greyscale filter.
   */
  public static OperationInterface createFilter(String operation, String sourceImageID,
                                                ImageDatabaseInterface imageDatabase) {

    if (operation.equalsIgnoreCase("luma")) {
      return new LumaOperation(imageDatabase.getImage(sourceImageID));
    } else if (operation.equalsIgnoreCase("intensity")) {
      return new IntensityOperation(imageDatabase.getImage(sourceImageID));
    } else if (operation.equalsIgnoreCase("value")) {
      return new ValueOperation(imageDatabase.getImage(sourceImageID));
    } else if (operation.equalsIgnoreCase("red")) {
      return new ColorComponentOperation(imageDatabase.getImage(sourceImageID), "red");
    } else if (operation.equalsIgnoreCase("green")) {
      return new ColorComponentOperation(imageDatabase.getImage(sourceImageID), "green");
    } else if (operation.equalsIgnoreCase("blue")) {
      return new ColorComponentOperation(imageDatabase.getImage(sourceImageID), "blue");
    } else {
      throw new IllegalArgumentException("Invalid operation. "
              + "Must be one of: luma, intensity, value, red, green, blue");
    }
  }
}
