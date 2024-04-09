package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import entity.Product;
import config.Config;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BucketJsonPojo {

    private final ObjectMapper objectMapper;
    private final File bucketFile;

    public BucketJsonPojo() {
        objectMapper = new ObjectMapper()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        bucketFile = new File(Config.PATH_CLIENTS_BUCKET_JSON);
    }

    public Map<String, List<Product>> readBuckets() throws IOException {
        if (bucketFile.exists()) {
            return objectMapper.readValue(bucketFile, new TypeReference<>() {});
        }
        return new HashMap<>();
    }

    public void writeBuckets(Map<String, List<Product>> buckets) throws IOException {
        objectMapper.writeValue(bucketFile, buckets);
    }

}
