package telran.b7a.accounting.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.b7a.accounting.models.UserAccount;

public interface AccountMongoRepository extends MongoRepository<UserAccount, String> {

}
