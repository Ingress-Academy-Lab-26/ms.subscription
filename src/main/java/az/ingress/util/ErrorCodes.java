package az.ingress.util;

import lombok.Getter;

@Getter
public enum ErrorCodes {
    OK("Uğurla icra edildi!"),
    NOT_FOUND("Tapılmadı");

    private String azMsg;

    ErrorCodes(String azMsg) {
        this.azMsg = azMsg;
    }

}
