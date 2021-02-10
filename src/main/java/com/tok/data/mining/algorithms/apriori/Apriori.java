package com.tok.data.mining.algorithms.apriori;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tok.data.mining.algorithms.AlgorithmName;
import com.tok.data.mining.algorithms.IAlgorithm;
import com.tok.data.mining.algorithms.IAlgorithmReturnModel;
import com.tok.data.mining.algorithms.models.AlgorithmModel;
import com.tok.data.mining.algorithms.models.AprioriInputModel;
import com.tok.data.mining.mongodb.MongoDbCentral;
import com.tok.data.mining.mongodb.model.CollectionColumnDataModel;
import com.tok.data.mining.payload.request.AlgorithmRequest;

@Component
public class Apriori implements IAlgorithm {
	
	@Autowired
	private MongoDbCentral mongoDbCentral;

	@Override
	public IAlgorithmReturnModel execute(AlgorithmRequest request) {

		AprioriInputModel model = (AprioriInputModel) getModel(request.getProperties());
		
		CollectionColumnDataModel data = mongoDbCentral.getColumnCollectionData(request.getDatabaseName(), request.getCollectionName(), request.getColumnName());
		
		List<String> simpleData = convertData(data.getData());
		
		//transactions count
		Integer numTransactions = simpleData.size();
		
		//count of items
		List<String> countedItems = countItems(simpleData);
		Integer countItems = countedItems.size();
		
		

		return null;
	}
	
	private List<String> countItems(List<String> simpleData) {
		List<String> countedItems = new ArrayList<>();
		simpleData.forEach(row -> {
			StringTokenizer stk = new StringTokenizer(row, ",");
			while(stk.hasMoreElements()) {
				if(!countedItems.contains(stk.nextToken())) {
					countedItems.add(stk.nextToken());
				}
			}
		});
		
		return countedItems;
	}
	
	
	private List<String> convertData(List<Object> data){
		List<String> convertedData = new ArrayList<>();
		data.forEach(o -> {
			if(o instanceof List) {
				StringBuffer buffer = new StringBuffer();
				((List<?>) o).forEach(e -> {
					buffer.append(e);
					buffer.append(",");
				});
				buffer.setLength(buffer.length() - 1);
				convertedData.add(buffer.toString());
			}
		});
		return convertedData;
	}

	@Override
	public AlgorithmName getName() {
		return AlgorithmName.APRIORI;
	}

	@Override
	public AlgorithmModel getModel(Object inputProperties) {
		if (inputProperties instanceof Map) {
			Map<?, ?> properties = (Map<?, ?>) inputProperties;
			AprioriInputModel inputModel = new AprioriInputModel();
			Field[] fields = inputModel.getClass().getDeclaredFields();

			for (Field f : fields) {
				Object obj = properties.get(f.getName());
				f.setAccessible(true);
				try {
					Class<?> type = f.getType();
					if (type.getSimpleName().equals("Double")) {
						if (obj != null)
							f.set(inputModel, new Double(obj.toString()));
					}
					if (type.getSimpleName().equals("Integer")) {
						if (obj != null)
							f.set(inputModel, new Integer(obj.toString()));
					}

				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}

			return inputModel;
		}

		return null;
	}

}
