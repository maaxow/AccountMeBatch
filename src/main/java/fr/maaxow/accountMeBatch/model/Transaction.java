package fr.maaxow.accountMeBatch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "transactions")
@Data
@AllArgsConstructor
public class Transaction {

    @Id
    private String id;

    @Field(type = FieldType.Date)
    private Date date;

    @Field(type = FieldType.Keyword)
    private String comment;

    @Field(type = FieldType.Keyword)
    private TransactionType type;

    @Field(type = FieldType.Double)
    private Double amount;

    @Field(type = FieldType.Object)
    private AdditionalInformation additionalInformation;

}
