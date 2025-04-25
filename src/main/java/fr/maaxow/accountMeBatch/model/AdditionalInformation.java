package fr.maaxow.accountMeBatch.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
public class AdditionalInformation {

    @Field(type = FieldType.Keyword)
    private String category;
    @Field(type = FieldType.Keyword)
    private String subcategory;
    @Field(type = FieldType.Boolean)
    private Boolean internalTransfer;
    @Field(type = FieldType.Keyword)
    private String accountTitle;
}
