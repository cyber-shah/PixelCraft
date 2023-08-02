package controller.commandmanager;

import controller.commandsstrategy.CommandStrategyInterface;
import controller.commandsstrategy.analysis.AnalysisCommandStrategy;
import controller.commandsstrategy.io.LoadCommandStrategy;
import controller.commandsstrategy.io.SaveCommandStrategy;
import controller.commandsstrategy.operations.colortransformation.ColorCommandStrategy;
import controller.commandsstrategy.operations.filter.FilterCommandStrategy;
import controller.commandsstrategy.operations.greyscale.GreyscaleCommandStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is the single source for CommandManagement all the commands.
 * Commands registered are of the type CommandStrategyInterface.
 * @see CommandStrategyInterface
 */
public class CommandsManager implements CommandsManagerInterface {
  private Map<String, CommandStrategyInterface> commandsMap;

  /**
   * Default constructor, initializes the commandsMap.
   */
  public CommandsManager() {
    commandsMap = new HashMap<>();
  }

  /**
   * This method registers the command passed.
   * @param commandName The name of the command.
   * @param commandStrategy The command strategy.
   */
  @Override
  public void registerCommand(String commandName, CommandStrategyInterface commandStrategy) {
    commandsMap.put(commandName, commandStrategy);
  }

  /**
   * This method registers all the commands.
   */
  @Override
  public void registerAllCommands() {
    // IO operations --------------------------------------
    commandsMap.put("LOAD", new LoadCommandStrategy());
    commandsMap.put("SAVE", new SaveCommandStrategy());
    // GREYSCALE operations -----------------------------------
    commandsMap.put("GREYSCALE", new GreyscaleCommandStrategy());
    // BRIGHTEN operations ------------------------------------
    commandsMap.put("BRIGHTEN", new controller.commandsstrategy.BrightenCommandStrategy());
    // FILTER ----------------------------------------------------
    commandsMap.put("FILTER", new FilterCommandStrategy());
    // COLOR TRANSFORMATIONS --------------------------------------
    commandsMap.put("COLOR", new ColorCommandStrategy());
    // ANALYSIS ---------------------------------------------------
    commandsMap.put("ANALYSIS", new AnalysisCommandStrategy());
  }

  /**
   * This method is a getter for the command strategy.
   * @param commandList The command list.
   * @return The command strategy if found, null otherwise.
   */
  @Override
  public CommandStrategyInterface getCommandStrategy(String[] commandList) {
    String commandName = commandList[0].toUpperCase();
    return commandsMap.getOrDefault(commandName, null);
  }

  /**
   * This method converts the commandsMap to a string.
   * @return String of all the commands registered.
   */
  @Override
  public String listAllCommands() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("load <file-path> <image-id> \n");
    stringBuilder.append("save <image-id> <file-path>\n");
    stringBuilder.append("greyscale <component> <image-id> <new-image-id>\n");
    stringBuilder.append("          <components> =  luma\n");
    stringBuilder.append("                          intensity\n");
    stringBuilder.append("                          value\n");
    stringBuilder.append("                          red\n");
    stringBuilder.append("                          green\n");
    stringBuilder.append("                          blue\n");
    stringBuilder.append("brighten <factor> <image-id> <new-image-id>\n");
    stringBuilder.append("filter <operation> <image-id> <new-image-id>\n");
    stringBuilder.append("       <operations> = blur\n");
    stringBuilder.append("                      sharpen\n");
    stringBuilder.append("color <operation> <image-id> <new-image-id>\n");
    stringBuilder.append("      <operations> = greyscale\n");
    stringBuilder.append("                     sepia\n");
    stringBuilder.append("analysis histogram <image-id> <new-histogram-id>\n");
    stringBuilder.append("exit\n");
    stringBuilder.append("list-all-images\n");
    stringBuilder.append("list-all-commands\n");
    return stringBuilder.toString();
  }

}
