package com.milkit.app.domain.greet;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Greeting {
	@ApiModelProperty(value="Greeting 키ID")
    private final long id;
	@ApiModelProperty(value="내용")
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}