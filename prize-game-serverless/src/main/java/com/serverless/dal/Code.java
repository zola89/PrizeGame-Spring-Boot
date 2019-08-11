package com.serverless.dal;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

@DynamoDBTable(tableName = "PLACEHOLDER_CODE_TABLE_NAME")
public class Code {
	// get the table name from env. var. set in serverless.yml
	private static final String CODE_TABLE_NAME = System.getenv("CODE_TABLE_NAME");

	private static DynamoDBAdapter db_adapter;
	private final AmazonDynamoDB client;
	private final DynamoDBMapper mapper;

	private Logger logger = Logger.getLogger(this.getClass());

	private String id;
	private String prize_code;
	private String prize_time;
	private String user_id;
	private String prize_type;
	
	
	
	@DynamoDBHashKey(attributeName = "id")
	@DynamoDBAutoGeneratedKey	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@DynamoDBRangeKey(attributeName = "prize_code")	
	public String getPrize_code() {
		return prize_code;
	}
	public void setPrize_code(String prize_code) {
		this.prize_code = prize_code;
	}

	@DynamoDBAttribute(attributeName = "prize_time")
	public String getPrize_time() {
		return prize_time;
	}
	public void setPrize_time(String prize_time) {
		this.prize_time = prize_time;
	}
	@DynamoDBIndexHashKey(attributeName = "user_id", globalSecondaryIndexName = "user_id-index")
	@DynamoDBAttribute(attributeName = "user_id")
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	@DynamoDBAttribute(attributeName = "prize_type")
	public String getPrize_type() {
		return prize_type;
	}
	public void setPrize_type(String prize_type) {
		this.prize_type = prize_type;
	}
	
	public Code() {
		// build the mapper config
		DynamoDBMapperConfig mapperConfig = DynamoDBMapperConfig.builder()
				.withTableNameOverride(new DynamoDBMapperConfig.TableNameOverride(CODE_TABLE_NAME)).build();
		// get the db adapter
		this.db_adapter = DynamoDBAdapter.getInstance();
		this.client = this.db_adapter.getDbClient();
		// create the mapper with config
		this.mapper = this.db_adapter.createDbMapper(mapperConfig);
	}
	
	// methods
	public Boolean ifTableExists() {
		return this.client.describeTable(CODE_TABLE_NAME).getTable().getTableStatus().equals("ACTIVE");
	}

	public List<Code> list() throws IOException {
		DynamoDBScanExpression scanExp = new DynamoDBScanExpression();
		List<Code> results = this.mapper.scan(Code.class, scanExp);
		for (Code p : results) {
			logger.info("Codes - list(): " + p.toString());
		}
		return results;
	}

	public Code get(String id) throws IOException {
		Code code = null;

		HashMap<String, AttributeValue> av = new HashMap<String, AttributeValue>();
		av.put(":v1", new AttributeValue().withS(id));

		DynamoDBQueryExpression<Code> queryExp = new DynamoDBQueryExpression<Code>()
				.withKeyConditionExpression("id = :v1").withExpressionAttributeValues(av);

		PaginatedQueryList<Code> result = this.mapper.query(Code.class, queryExp);
		if (result.size() > 0) {
			code = result.get(0);
			logger.info("Codes - get(): Code - " + code.toString());
		} else {
			logger.info("Codes - get(): Code - Not Found.");
		}
		return code;
	}

	public void save(Code code) throws IOException {
		logger.info("Codes - save(): " + code.toString());
		this.mapper.save(code);
	}

	public void update(Code code) throws IOException {
		logger.info("Codes - update(): " + code.toString());

		DynamoDBMapperConfig dynamoDBMapperConfig = new DynamoDBMapperConfig.Builder()
				.withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT)
				.withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE).build();
		this.mapper.save(code, dynamoDBMapperConfig);
	}

	public Boolean delete(String id) throws IOException {
		Code Code = null;

		// get Code if exists
		Code = get(id);
		if (Code != null) {
			logger.info("Codes - delete(): " + Code.toString());
			this.mapper.delete(Code);
		} else {
			logger.info("Codes - delete(): Code - does not exist.");
			return false;
		}
		return true;
	}
	public List<Code> getCodesByUser_id(String userId) {

		HashMap<String, AttributeValue> av = new HashMap<String, AttributeValue>();
		av.put(":v1", new AttributeValue().withS(userId));

		DynamoDBQueryExpression<Code> queryExp = new DynamoDBQueryExpression<Code>().withIndexName("user_id-index")
				.withConsistentRead(false).withKeyConditionExpression("user_id = :v1")
				.withExpressionAttributeValues(av);

		PaginatedQueryList<Code> result = this.mapper.query(Code.class, queryExp);
		if (result.size() > 0) {
			for(Code nextCode : result) {
			logger.info("Codes - getCodesByUser_id() for User[ "+ userId + " ]" + " Code - " + nextCode.toString());
			}
		} else {
			logger.info("Codes - getCodesByUser_id(): Code - Not Found.");
		}
		return result;
	}
	public Code getCodeByPrize_code(String prizeCode) {
		Code code = null;

		HashMap<String, AttributeValue> av = new HashMap<String, AttributeValue>();
		av.put(":v1", new AttributeValue().withS(prizeCode));

		DynamoDBQueryExpression<Code> queryExp = new DynamoDBQueryExpression<Code>()
				.withKeyConditionExpression("prize_code = :v1").withExpressionAttributeValues(av);

		PaginatedQueryList<Code> result = this.mapper.query(Code.class, queryExp);
		if (result.size() > 0) {
			code = result.get(0);
			logger.info("Codes - getCodeByPrize_code(): Code - " + code.toString());
		} else {
			logger.info("Codes - getCodeByPrize_code(): Code - Not Found.");
		}
		return code;
	}
}