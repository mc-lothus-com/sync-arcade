package com.lothus.sync.stats.data.stats;

import com.lothus.core.Core;
import com.lothus.sync.stats.data.type.DataType;
import com.lothus.sync.stats.player.games.bedwars.stats.BedStats;
import com.lothus.sync.stats.player.games.skywars.stats.SkyStats;
import com.lothus.sync.stats.player.games.skywars.stats.ranked.SkyRanked;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class DataStats {

    private MongoCollection<Document> collection;


    public int create(DataType dataType, SkyRanked skyRanked) {
        collection = getCollection(dataType);
        Document found = collection.find(Filters.eq("uniqueId", skyRanked.getUniqueId().toString())).first();
        if (found == null) {
            try {
                found = Document.parse(Core.getGson().toJson(skyRanked));
                collection.insertOne(found);
                return 1;
            } catch (Exception e) {
                Core.getLogger().info("Não foi possível criar as estatísticas de " + skyRanked.getUniqueId().toString() + ".");
                e.printStackTrace();
                return 0;
            }
        }
        return -1;
    }
    public int create(DataType dataType, BedStats bedStats) {
        collection = getCollection(dataType);
        Document found = collection.find(Filters.eq("uniqueId", bedStats.getUniqueId().toString())).first();
        if (found == null) {
            try {
                found = Document.parse(Core.getGson().toJson(bedStats));
                collection.insertOne(found);
                return 1;
            } catch (Exception e) {
                Core.getLogger().info("Não foi possível criar as estatísticas de " + bedStats.getUniqueId().toString() + ".");
                e.printStackTrace();
                return 0;
            }
        }
        return -1;
    }

    public int create(DataType dataType, SkyStats skyStats) {
        collection = getCollection(dataType);
        Document found = collection.find(Filters.eq("uniqueId", skyStats.getUniqueId().toString())).first();
        if (found == null) {
            try {
                found = Document.parse(Core.getGson().toJson(skyStats));
                collection.insertOne(found);
                return 1;
            } catch (Exception e) {
                Core.getLogger().info("Não foi possível criar as estatísticas de " + skyStats.getUniqueId().toString() + ".");
                e.printStackTrace();
                return 0;
            }
        }
        return -1;
    }

    public int update(DataType dataType, SkyStats skyStats) {
        collection = getCollection(dataType);
        try {
            collection.updateOne(Filters.eq("uniqueId", skyStats.getUniqueId().toString()),
                    new Document("$set", Document.parse(Core.getGson().toJson(skyStats))));
            Core.getRedis().set(dataType.getRedisPrefix() + skyStats.getUniqueId(), Core.getGson().toJson(skyStats));
            return 1;
        } catch (Exception e) {
            Core.getLogger().info("Não foi possível atualizar as estatísticas de " + skyStats.getUniqueId() + ".");
            return 0;
        }
    }


    public int update(DataType dataType, BedStats bedStats) {
        collection = getCollection(dataType);
        try {
            collection.updateOne(Filters.eq("uniqueId", bedStats.getUniqueId().toString()),
                    new Document("$set", Document.parse(Core.getGson().toJson(bedStats))));
            Core.getRedis().set(dataType.getRedisPrefix() + bedStats.getUniqueId(), Core.getGson().toJson(bedStats));
            return 1;
        } catch (Exception e) {
            Core.getLogger().info("Não foi possível atualizar as estatísticas de " + bedStats.getUniqueId() + ".");
            return 0;
        }
    }

    private MongoCollection<Document> getCollection(DataType dataType) {
        return Core.getMongo().getDatabase(dataType.getDatabase()).getCollection(dataType.getCollection());
    }

    public int delete(DataType dataType, UUID uniqueId) {
        collection = getCollection(dataType);
        try {
            Document found = collection.find(Filters.eq("uniqueId", uniqueId.toString())).first();
            if (found != null) {
                collection.deleteOne(Filters.eq("uniqueId", uniqueId.toString()));
                return 1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
        return -1;
    }

    public BedStats getBedStats(DataType dataType, UUID uniqueId) {
        collection = getCollection(dataType);
        Document found = collection.find(Filters.eq("uniqueId", uniqueId.toString())).first();
        if (found != null) {
            return Core.getGson().fromJson(Core.getGson().toJson(found), BedStats.class);
        }
        return null;
    }

    public SkyStats getSkyStats(DataType dataType, UUID uniqueId) {
        collection = getCollection(dataType);
        Document found = collection.find(Filters.eq("uniqueId", uniqueId.toString())).first();
        if (found != null) {
            return Core.getGson().fromJson(Core.getGson().toJson(found), SkyStats.class);
        }
        return null;
    }
    public SkyRanked getSkyRanked(DataType dataType, UUID uniqueId) {
        collection = getCollection(dataType);
        Document found = collection.find(Filters.eq("uniqueId", uniqueId.toString())).first();
        if (found != null) {
            return Core.getGson().fromJson(Core.getGson().toJson(found), SkyRanked.class);
        }
        return null;
    }


    public Collection<BedStats> getBedRanking(DataType dataType, String fieldName) {
        collection = getCollection(dataType);
        List<BedStats> list = new ArrayList<>();
        for (Document element : collection.find().sort(new Document(fieldName, -1)).limit(10)) {
            list.add(Core.getGson().fromJson(Core.getGson().toJson(element), BedStats.class));
        }
        return list;
    }

    public Collection<SkyStats> getSkyRanking(DataType dataType, String fieldName) {
        collection = getCollection(dataType);
        List<SkyStats> list = new ArrayList<>();
        for (Document element : collection.find().sort(new Document(fieldName, -1)).limit(10)) {
            list.add(Core.getGson().fromJson(Core.getGson().toJson(element), SkyStats.class));
        }
        return list;
    }

    public Collection<SkyRanked> getSkyRanked(DataType dataType, String fieldName) {
        collection = getCollection(dataType);
        List<SkyRanked> list = new ArrayList<>();
        for (Document element : collection.find().sort(new Document(fieldName, -1)).limit(10)) {
            list.add(Core.getGson().fromJson(Core.getGson().toJson(element), SkyRanked.class));
        }
        return list;
    }
}
