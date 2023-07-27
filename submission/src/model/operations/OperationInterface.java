package model.operations;

import model.image.CustomImageState;

/**
 * This interface represents an Operation.
 * It represents an Operation that can be applied to an Image.
 */
public interface OperationInterface {

  CustomImageState applyOperation();
}
