package ru.eatthefrog.hatterBot.telegramapi;

public class Token {
    String content;
    Boolean isValid;

    public Token(String content, Boolean isValid) {
        this.content = content;
        this.isValid = isValid;
    }

    public String getContent() {
        return content;
    }

    public Boolean getValid() {
        return isValid;
    }
}
