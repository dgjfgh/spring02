package com.panpom.springmvc01.util;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisUtils {

    //情况一：key不存在，返回-2
    //情况二：key存在，但没有设置超时，返回-1
    //情况三：key存在，设置有超时时间，返回实际的秒数
    public static boolean isSetExpTime(RedisTemplate<String, Object> redisTemplate, String key) {
        if (redisTemplate.getExpire(key).intValue() > 0) {
            return true;
        }
        return false;
    }

    public static List<String> getKeys(RedisTemplate<String, Object> redisTemplate, String key) {
        // 列出所有匹配的key
        Set<String> keySet = redisTemplate.keys(key + "*");
        if (keySet == null || keySet.size() <= 0) {
            return null;
        }
        List<String> tmp = new ArrayList<String>(keySet);
//		for (Object str : keySet.toArray()) {
//			if (str.toString().contains(key)) {
//				tmp.add(str.toString());
//			}
//		}
        return tmp;
    }

    public static void delKey(RedisTemplate<String, Object> redisTemplate,
                              String key) {
        // 列出所有匹配的key
//		Set<String> keySet = redisTemplate.keys(key+"*");
//		if (keySet == null || keySet.size() <= 0) {
//			return;
//		}
//		Set<String> tmp = new HashSet<String>();
//		for (Object str : keySet.toArray()) {
//			if (str.toString().startsWith(key)) {
//				tmp.add(str.toString());
//			}
//		}
//		if (tmp.size() > 0)
        redisTemplate.delete(key);
    }

    public static Object getValue(RedisTemplate<?, ?> redisTemplate, String key) {
        if (key == null || key.length() == 0) {
            return null;
        }
        ValueOperations<?, ?> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    public static void setStringValue(RedisTemplate redisTemplate, String key,
                                      Object value) {
        ValueOperations operation = redisTemplate.opsForValue();
        operation.set(key, value);
    }

    public static void setStringValue(RedisTemplate redisTemplate, String key,
                                      Object value, long time) {
        ValueOperations operation = redisTemplate.opsForValue();
//		if(!isSetExpTime(redisTemplate, key)){
        operation.set(key, value, time, TimeUnit.SECONDS);
//		}else{
//			operation.set(key, value);
//		}
    }

    /**
     * 缓存List数据
     *
     * @param key      缓存的键值
     * @param dataList 待缓存的List数据
     * @return 缓存的对象
     */
    public static ListOperations<String, Object> setCacheList(
            RedisTemplate<String, Object> redisTemplate, String key,
            List<Object> dataList) {
        ListOperations<String, Object> listOperation = redisTemplate
                .opsForList();
        if (null != dataList) {
            int size = dataList.size();
            for (int i = 0; i < size; i++) {
                if (dataList.get(i) != null) {
                    listOperation.rightPush(key, dataList.get(i));
                }
            }
        }

        return listOperation;
    }

    /**
     * 清除list
     *
     * @param redisTemplate
     * @param key
     * @return
     */
    public static long clearCacheList(RedisTemplate<?, ?> redisTemplate,
                                      final String key) {
        return (Long) redisTemplate.execute(new RedisCallback<Object>() {
            public Long doInRedis(RedisConnection connection)
                    throws DataAccessException {
                long result = 0;
                result = connection.del(key.getBytes());
                return result;
            }
        });
    }

    /**
     * 获得缓存的list对象
     *
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     */
    public static List<Object> getCacheList(
            RedisTemplate<String, Object> redisTemplate, String key) {
        List<Object> dataList = new ArrayList<Object>();
        ListOperations<String, Object> listOperation = redisTemplate
                .opsForList();
        Long size = listOperation.size(key);

        for (int i = 0; i < size; i++) {
            dataList.add((Object) listOperation.leftPop(key));
        }
        return dataList;
    }

    public static int incr(RedisTemplate redisTemplate, String key) {
        return redisTemplate.opsForValue().increment(key, 1).intValue();
    }
}
