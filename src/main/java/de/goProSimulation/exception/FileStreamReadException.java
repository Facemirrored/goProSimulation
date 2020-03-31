package de.goProSimulation.exception;

public class FileStreamReadException extends RuntimeException {

  private static final long serialVersionUID = -2735786307617894846L;

  public FileStreamReadException(final String message) {
    super(message);
  }
}
