package de.goProSimulation.exception;

public class InvalidInputDataException extends RuntimeException {

  private static final long serialVersionUID = -211081342583439332L;

  public InvalidInputDataException(final String message) {
    super(message);
  }
}
