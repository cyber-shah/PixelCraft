package controller.commandsstrategy.operations.colortransformation;

import model.ImageDatabaseInterface;
import model.operations.OperationInterface;
import model.operations.color.GreyscaleColor;
import model.operations.color.SepiaColor;

/**
 * This class is a factory for creating color filters.
 * It implements the Factory design pattern.
 */
public class ColorFactory {

  /**
   * This method produces a filter based on the operation.
   *
   * @param operation the operation to be performed.
   * @param sourceImageID the source image ID.
   * @param imageDatabase the image database.
   * @return the filter.
   * @throws IllegalArgumentException if the operation is not supported.
   */
  public static OperationInterface createFilter(String operation, String sourceImageID,
                                                ImageDatabaseInterface imageDatabase) {

    if (operation.equalsIgnoreCase("sepia")) {
      return new SepiaColor(imageDatabase.getImage(sourceImageID));
    } else if (operation.equalsIgnoreCase("greyscale")) {
      return new GreyscaleColor(imageDatabase.getImage(sourceImageID));
    } else {
      throw new IllegalArgumentException("Invalid operation. "
              + "Must be one of: sepia or greyscale");
    }
  }

}
