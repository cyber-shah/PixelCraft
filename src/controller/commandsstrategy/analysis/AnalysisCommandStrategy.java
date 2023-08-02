package controller.commandsstrategy.analysis;

import controller.commandsstrategy.CommandStrategyInterface;
import model.ImageDatabaseInterface;
import model.analysis.AnalysisInterface;

public class AnalysisCommandStrategy implements CommandStrategyInterface {

  @Override
  public void run(String[] commandsList, ImageDatabaseInterface histogramDatabase) {
    // 0. Validate all the arguments.
    if (commandsList.length < 3) {
      throw new IllegalStateException("Must be of the format: \n"
              + " \"analysis <operation> <sourceImageID> <new-graph-id>\"");
    }

    // 1. Set the sourceImageID and the destinationID.
    String operation = commandsList[1];
    String sourceImageID = commandsList[2];
    String graphID = commandsList[3];

    // 2. Check the operation and apply the filter.
    // SOLID : using Open-Closed principle here.
    //         We can add new filters without changing the code.
    //         only modify the FilterFactory class.
    AnalysisInterface analysis = AnalysisFactory.createAnalysis(operation, sourceImageID, histogramDatabase);
    analysis.runAnalysis();
  }
}
