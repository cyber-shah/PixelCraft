package controller.commandsstrategy.operations.filter;

import model.ImageDatabaseInterface;
import model.operations.OperationInterface;
import model.operations.filters.BlurFilter;
import model.operations.filters.SharpenFilter;

/**
 * This class is a factory for creating filters.
 */
public class FilterFactory {

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
    if (operation.equalsIgnoreCase("blur")) {
      return new BlurFilter(imageDatabase.getImage(sourceImageID));
    } else if (operation.equalsIgnoreCase("sharpen")) {
      return new SharpenFilter(imageDatabase.getImage(sourceImageID));
    } else {
      throw new IllegalArgumentException("Invalid operation. "
              + "Must be one of: blur or sharpen");
    }
  }
}
