package controller.commandsstrategy.analysis;

import model.ImageDatabaseInterface;
import model.analysis.AnalysisInterface;
import model.analysis.MakeHistogram;

public class AnalysisFactory {


  /**
   * This method produces a filter based on the operation.
   *
   * @param operation the operation to be performed.
   * @param sourceImageID the source image ID.
   * @param imageDatabase the image database.
   * @return the filter.
   * @throws IllegalArgumentException if the operation is not supported.
   */
  public static AnalysisInterface createAnalysis(String operation, String sourceImageID,
                                               ImageDatabaseInterface histogramDatabase) {

    if (operation.equalsIgnoreCase("histogram")) {
      return new MakeHistogram(sourceImageID, histogramDatabase);
    } else {
      throw new IllegalArgumentException("Invalid operation. "
              + "Must be one of: sepia or greyscale");
    }
  }

}
