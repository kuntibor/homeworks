package xyz.codingmentor.tiborkun.webshop.dto;

/**
 *
 * @author Tibor Kun
 */
public class ErrorDTO {

    private String errorMessage;

    public ErrorDTO() {
        //nothing to initialize
    }

    public ErrorDTO(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
