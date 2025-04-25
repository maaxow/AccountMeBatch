package fr.maaxow.accountMeBatch.repository;

import fr.maaxow.accountMeBatch.model.Transaction;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TransactionRepository extends ElasticsearchRepository<Transaction, String> {
}
