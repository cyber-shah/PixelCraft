package controller;

import controller.commandmanager.CommandsManager;
import controller.commandmanager.CommandsManagerInterface;
import controller.commandsstrategy.CommandStrategyInterface;
import model.ImageDatabase;
import model.ImageDatabaseInterface;
import model.image.CustomImageState;
import view.gui.CustomEvent;
import view.gui.GUIView;

import java.util.Objects;
import java.util.Stack;
import java.util.UUID;

/**
 * This class is the controller for the GUI.
 * It implements the ControllerGUIInterface.
 */
public class ControllerGUI implements ControllerGUIInterface {
  private final ImageDatabaseInterface imageDatabase;
  private final ImageDatabaseInterface histogramDatabase;
  private final Stack<CustomImageState> histogramStack;
  private final GUIView view;
  private final CommandsManagerInterface commandsManager;


  /**
   * This constructor creates a new ControllerGUI object.
   *
   * @param imageDatabase the model.
   * @param view the view.
   */
  public ControllerGUI(ImageDatabaseInterface imageDatabase, GUIView view) {
    this.imageDatabase = Objects.requireNonNull(imageDatabase);
    this.view = Objects.requireNonNull(view);
    // NOTE : Subscribe to the view's custom events.
    view.addEventsListener(this);
    this.commandsManager = new CommandsManager();
    commandsManager.registerAllCommands();
    this.histogramDatabase = new ImageDatabase();
    this.histogramStack = new Stack<>();
  }

  /**
   * This method runs the program.
   *
   * @param event the event to be handled.
   */
  @Override
  public void handleEvent(CustomEvent event) {

    CustomImageState updatedImage = null;

    // NOTE : 1. IO events are handled differently from other events.
    if (event.getEventType().equalsIgnoreCase("io")) {
      // NOTE : 2. IO events always use source ID and destination ID as null
      //        <load/save> <filePath> <imageID>
      String[] commandList = new String[3];
      commandList[0] = event.getEventName();
      commandList[1] = event.getFilePath();
      commandList[2] = event.getSourceID();
      try {
        // get the command strategy object from the commands manager
        CommandStrategyInterface commandStrategyObject =
                commandsManager.getCommandStrategy(commandList);
        // run the command
        commandStrategyObject.run(commandList, this.imageDatabase);

        updatedImage = this.imageDatabase.getImage(event.getSourceID());
      }
      catch (Exception e) {
        view.showMessage(e.getMessage());
      }
    }

    // for all other events, source ID is the source image ID
    // destination ID is the destination and file path is null
    else {
      // NOTE : command of the format:
      //        <eventType> <eventName> <sourceID> <destID>
      String[] commandList = new String[4];
      commandList[0] = event.getEventType(); // filter, color, brighten, greyscale ...
      commandList[1] = event.getEventName(); // blur, sharpen, sepia OR VALUE ...
      commandList[2] = event.getSourceID(); // source image ID
      commandList[3] = event.getDestID(); // destination image ID
      try {
        // get the command strategy object from the commands manager
        CommandStrategyInterface commandStrategyObject =
                commandsManager.getCommandStrategy(commandList);
        // run the command
        commandStrategyObject.run(commandList, this.imageDatabase);

        updatedImage = this.imageDatabase.getImage(event.getDestID());
      }
      catch (Exception e) {
        view.showMessage(e.getMessage());
      }
    }

    /**
     * histograms can also be created with scripting
     * <histogram> <source-image-ID> <histogram-dest-ID>
     *   so it will take in 2 params.
     *   1. source image ID from image database.
     *   2. new histogram ID for histogram database.
     *   ------------------------------------
     *   Therefore, there would be two parallel databases.
     *   1. image database
     *   2. histogram database
     *   ------------------------------------
     */
    if (event.getHistogram()) {

      String[] histogramCommand = new String[3];
      histogramCommand[0] = "histogram";
      histogramCommand[1] = event.getDestID();
      histogramCommand[2] = UUID.randomUUID().toString();
      try {
        // get the command strategy object from the commands manager
        CommandStrategyInterface commandStrategyObject =
                commandsManager.getCommandStrategy(histogramCommand);
        // run the command
        commandStrategyObject.run(histogramCommand, this.imageDatabase);

        // get the histogram
        CustomImageState histogram = this.histogramDatabase.getImage(histogramCommand[2]);

        // update it in the view
        view.updateHistogram(histogram);
      }
      catch (Exception e) {
        view.showMessage(e.getMessage());
      }
    }


    // show the message
    view.showMessage(event.getEventType() + " "
            + event.getEventName() + " " + "executed successfully");

    // update the view
    view.updateImageCanvas(updatedImage);
  }
}
