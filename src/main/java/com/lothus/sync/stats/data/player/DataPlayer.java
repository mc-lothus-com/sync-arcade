package com.lothus.sync.stats.data.player;

import com.lothus.core.Core;
import com.lothus.core.player.LothPlayer;
import com.lothus.sync.stats.data.type.DataType;
import com.lothus.sync.stats.player.games.bedwars.BedPlayer;
import com.lothus.sync.stats.player.games.skywars.SkyPlayer;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class DataPlayer {

    private MongoCollection<Document> collection;

    public int create(DataType dataType, BedPlayer bedPlayer) {
        collection = getCollection(dataType);
        Document found = collection.find(Filters.eq("uniqueId", bedPlayer.getUniqueId().toString())).first();
        if (found == null) {
            try {
                found = Document.parse(Core.getGson().toJson(bedPlayer));
                collection.insertOne(found);
                return 1;
            } catch (Exception e) {
                Core.getLogger().info("Não foi possível criar a conta de " + bedPlayer.getUniqueId() + ".");
                e.printStackTrace();
                return 0;
            }
        }
        return -1;
    }


    public int create(DataType dataType, SkyPlayer skyPlayer) {
        collection = getCollection(dataType);
        Document found = collection.find(Filters.eq("uniqueId", skyPlayer.getUniqueId().toString())).first();
        if (found == null) {
            try {
                found = Document.parse(Core.getGson().toJson(skyPlayer));
                collection.insertOne(found);
                return 1;
            } catch (Exception e) {
                Core.getLogger().info("Não foi possível criar a conta de " + skyPlayer.getUniqueId() + ".");
                e.printStackTrace();
                return 0;
            }
        }
        return -1;
    }

    public int update(DataType dataType, SkyPlayer skyPlayer) {
        collection = getCollection(dataType);
        try {
            LothPlayer lothPlayer = Core.getPlayerController().get(skyPlayer.getUniqueId());
            collection.updateOne(Filters.eq("uniqueId", skyPlayer.getUniqueId().toString()),
                    new Document("$set", Document.parse(Core.getGson().toJson(skyPlayer))));
            Core.getRedis().set(dataType.getRedisPrefix() + skyPlayer.getUniqueId(), Core.getGson().toJson(skyPlayer));
            return 1;
        } catch (Exception e) {
            Core.getLogger().info("Não foi possível atualizar a conta de " + skyPlayer.getUniqueId() + ".");
            return 0;
        }
    }

    public int update(DataType dataType, BedPlayer bedPlayer) {
        collection = getCollection(dataType);
        try {
            collection.updateOne(Filters.eq("uniqueId", bedPlayer.getUniqueId().toString()),
                    new Document("$set", Document.parse(Core.getGson().toJson(bedPlayer))));
            Core.getRedis().set(dataType.getRedisPrefix() + bedPlayer.getUniqueId(), Core.getGson().toJson(bedPlayer));
            return 1;
        } catch (Exception e) {
            Core.getLogger().info("Não foi possível atualizar a conta de " + bedPlayer.getUniqueId() + ".");
            return 0;
        }
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

    public BedPlayer getBed(DataType dataType, UUID uniqueId) {
        collection = getCollection(dataType);
        Document found = collection.find(Filters.eq("uniqueId", uniqueId.toString())).first();
        if (found != null) {
            return Core.getGson().fromJson(Core.getGson().toJson(found), BedPlayer.class);
        }
        return null;
    }

    public SkyPlayer getSky(DataType dataType, UUID uniqueId) {
        collection = getCollection(dataType);
        Document found = collection.find(Filters.eq("uniqueId", uniqueId.toString())).first();
        if (found != null) {
            return Core.getGson().fromJson(Core.getGson().toJson(found), SkyPlayer.class);
        }
        return null;
    }

    public BedPlayer getBed(DataType dataType, String name) {
        collection = getCollection(dataType);
        Document found = collection.find(Filters.eq("name", name)).first();
        if (found != null) {
            return Core.getGson().fromJson(Core.getGson().toJson(found), BedPlayer.class);
        }
        return null;
    }

    public SkyPlayer getSky(DataType dataType, String name) {
        collection = getCollection(dataType);
        Document found = collection.find(Filters.eq("name", name)).first();
        if (found != null) {
            return Core.getGson().fromJson(Core.getGson().toJson(found), SkyPlayer.class);
        }
        return null;
    }


    public Collection<BedPlayer> getBedRanking(DataType dataType, String fieldName) {
        collection = getCollection(dataType);
        List<BedPlayer> list = new ArrayList<>();
        for (Document element : collection.find().sort(new Document(fieldName, -1)).limit(10)) {
            list.add(Core.getGson().fromJson(Core.getGson().toJson(element), BedPlayer.class));
        }
        return list;
    }

    public Collection<BedPlayer> getLeagueRanking() {
        collection = getCollection(DataType.BED_WARS_ACCOUNT);
        List<BedPlayer> list = new ArrayList<>();
        for (Document element : collection.find().sort(new Document("leagueId", -1)).sort(new Document("points", -1)).limit(10)) {
            list.add(Core.getGson().fromJson(Core.getGson().toJson(element), BedPlayer.class));
        }
        return list;
    }


    public Collection<SkyPlayer> getSkyRanking(DataType dataType, String fieldName) {
        collection = getCollection(dataType);
        List<SkyPlayer> list = new ArrayList<>();
        for (Document element : collection.find().sort(new Document(fieldName, -1)).limit(10)) {
            list.add(Core.getGson().fromJson(Core.getGson().toJson(element), SkyPlayer.class));
        }
        return list;
    }


    private MongoCollection<Document> getCollection(DataType dataType) {
        return Core.getMongo().getDatabase(dataType.getDatabase()).getCollection(dataType.getCollection());
    }
}
