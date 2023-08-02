package model.analysis;

import model.ImageDatabaseInterface;
import model.image.CustomImageState;

/**
 * This class represents the operation to make a histogram of an image.
 * It implements the OperationInterface.
 */
// TODO : implement the AnalysisInterface.
public class MakeHistogram implements AnalysisInterface {
  private CustomImageState image;
  private ImageDatabaseInterface histogramDatabase;

  /**
   * This constructor creates a new MakeHistogram object.
   *
   * @param image the image to be made into a histogram.
   */
  public MakeHistogram(CustomImageState image, ImageDatabaseInterface histogramDatabase) {
    this.image = image;
    this.histogramDatabase = histogramDatabase;
  }

  /**
   * 1. Create arrays for each color channel. - 256
   * 2. Iterate through the image and increment the count of each color channel.
   * 3. Create a new image with the histogram.
   */
  @Override
  public void runAnalysis() {
    int[] redHistogram = new int[256];
    int[] greenHistogram = new int[256];
    int[] blueHistogram = new int[256];
    int[] intensityHistogram = new int[256];

    int width = image.getWidth();
    int height = image.getHeight();

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j ++) {
        int red = image.getPixel(i, j).getRed();
        int green = image.getPixel(i, j).getGreen();
        int blue = image.getPixel(i, j).getBlue();
        int intensity = (red + green + blue) / 3;
        redHistogram[red] += 1;
        greenHistogram[green] += 1;
        blueHistogram[blue] += 1;
        intensityHistogram[intensity] += 1;
      }
    }
//    return null;
  }
}
