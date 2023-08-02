package view.gui;

/**
 * This class is like a wrapper for the EventObject class.
 * It will be used to generate custom events.
 * SOLID : why are we using CustomEvents? and not key events? or mouse events? or any other events?
 *        So that this is not platform dependent. We can use this interface to generate events
 *        for any platform. Like gestures for mobile devices, key events for desktops, etc.
 */
public class CustomEvent extends java.util.EventObject {

  private final String eventName;
  private final String eventType;
  private final String filePath;
  private final String sourceID;
  private final String destID;
  private final boolean getHistogram;

  /**
   * Constructor for the custom event.
   * This is non-mutable.
   *
   * @param source the source of the event.
   * @param eventName the name of the event.
   * @param filePath the path of the file.
   * @param sourceID the source ID.
   * @param destID the destination ID.
   */
  public CustomEvent(Object source, String eventType, String eventName,
                     String filePath, String sourceID, String destID, boolean getHistogram) {
    super(source);
    this.eventType = eventType;
    this.eventName = eventName;
    this.filePath = filePath;
    this.sourceID = sourceID;
    this.destID = destID;
    this.getHistogram = getHistogram;
  }

  /**
   * Getter for the event name.
   */
  public String getEventName() {
    return eventName;
  }

  /**
   * Getter for the file path.
   */
  public String getFilePath() {
    return filePath;
  }

  /**
   * Getter for the source ID.
   */
  public String getSourceID() {
    return sourceID;
  }

  /**
   * Getter for the destination ID.
   */
  public String getDestID() {
    return destID;
  }

  /**
   * Getter for the event type.
   */
  public String getEventType() {
    return eventType;
  }

  /**
   * Getter for the histogram.
   *
   * @return true if histogram is to be generated, false otherwise.
   */
  public boolean getHistogram() {
    return getHistogram;
  }



}
