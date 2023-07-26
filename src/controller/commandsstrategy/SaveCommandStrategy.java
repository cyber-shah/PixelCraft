package controller.commandsstrategy;

import controller.io.ImageSaverInterface;
import controller.io.PPMImageSaver;
import model.ImageDatabaseInterface;
import model.image.CustomImageState;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedWriter;

/**
 * This class implements the CommandStrategyInterface and is responsible for saving an image.
 * @see CommandStrategyInterface
 * @see ImageDatabaseInterface
 */
public class SaveCommandStrategy implements CommandStrategyInterface {

  /**
   * This method runs the command.
   * NOTE: This method calls the ImageSaver to save the image.
   *       Also uses the BufferedWrite to write the image to the file.
   *
   * @param commandsList String[] a list of commands.
   * @param model        ImageDatabaseInterface the model to be used.
   * @throws IllegalArgumentException if the image cannot be saved.
   */
  @Override
  public void run(String[] commandsList, ImageDatabaseInterface model) {
    // 0. Validate all the arguments.
    if (commandsList.length != 4) {
      throw new IllegalArgumentException("Please provide the command in this format" +
              " \"save format imageID destImagePath\"");
    }

    // 1. Set up all the arguments
    String format = commandsList[1];
    String imageID = commandsList[2];
    String destImagePath = commandsList[3];
    // 1. if imageID is not present in the model
    if (!model.containsImage(imageID)) {
      throw new IllegalArgumentException("Image not found");
    }
    CustomImageState imageWrite = model.getImage(imageID);

    // 2. if format is PPM
    if (format.equalsIgnoreCase("PPM")) {
      // 3. call the ImageSaver to save the image.
      ImageSaverInterface imageSaver = new PPMImageSaver();
      try {
        imageSaver.save(imageWrite, destImagePath);
      } catch (Exception e) {
        throw new IllegalArgumentException(e.getMessage());
      }
    }

    // 4. if format is not PPM
    else {
      // 5. call the ImageIO to write the image to the file.
      try {
        BufferedImage bufferedImage = imageWrite.
        ImageIO.write(imageWrite, format, new java.io.File(destImagePath));
      }
      catch (Exception e) {
        throw new IllegalArgumentException(e.getMessage());
      }
    }
  }
}
