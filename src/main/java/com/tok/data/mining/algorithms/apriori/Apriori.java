package com.tok.data.mining.algorithms.apriori;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tok.data.mining.algorithms.AlgorithmName;
import com.tok.data.mining.algorithms.AlgorithmReturnModel;
import com.tok.data.mining.algorithms.IAlgorithm;
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
	public AlgorithmReturnModel execute(AlgorithmRequest request) {

		AprioriInputModel model = (AprioriInputModel) getModel(request.getProperties());
		
		CollectionColumnDataModel data = mongoDbCentral.getColumnCollectionData(request.getDatabaseName(), request.getCollectionName(), request.getColumnName());
		
		List<String> simpleData = convertData(data.getData());
		
		//transactions count
		Integer numTransactions = simpleData.size();
		
		//count of items
		List<String> countedItems = countItems(simpleData);
		Integer countItems = countedItems.size();
		
		// output
		StringBuffer outputBuffer = new StringBuffer();
		initOutput(outputBuffer, countItems, numTransactions, model.getMinimumSupport(), model.getMinimumConfidence());

		
		
		
		return new AlgorithmReturnModel(outputBuffer.toString());
	}
	
	private void initOutput(StringBuffer buffer, Integer countItems, Integer numTransactions, Double minSup, Double minConf) {
		buffer.append("\n Input configuration: ");
		buffer.append(countItems);
		buffer.append(" items, ");
		buffer.append(numTransactions);
		buffer.append(" transactions, ");
		buffer.append(" minimumSupport ");
		buffer.append(minSup);
		buffer.append(", minimumConfidence ");
		buffer.append(minConf);
	}
	
	private List<String> countItems(List<String> simpleData) {
		final List<String> countedItems = new ArrayList<>();
		
		for(String row : simpleData) {
			StringTokenizer stk = new StringTokenizer(row, ",");
			while(stk.hasMoreElements()) {
				String element = stk.nextToken();
				if(!countedItems.contains(element)) {
					countedItems.add(element);
				}
			}
		}
		
		simpleData.forEach(row -> {
			
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
