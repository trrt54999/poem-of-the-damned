package com.midnightdraft.poemofthedamned.infrastructure.repository.impl;

import com.midnightdraft.poemofthedamned.domain.model.GameCharacterSprite;
import com.midnightdraft.poemofthedamned.infrastructure.exception.RepositoryException.EntityFetchException;
import com.midnightdraft.poemofthedamned.domain.repository.GameCharacterSpriteRepository;
import com.midnightdraft.poemofthedamned.infrastructure.util.HibernateSessionFactory;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

@Slf4j
public class GameCharacterSpriteRepositoryImpl extends BaseRepositoryImpl<GameCharacterSprite> implements
    GameCharacterSpriteRepository {

  public GameCharacterSpriteRepositoryImpl(){
    super(GameCharacterSprite.class);
  }

  @Override
  public Optional<GameCharacterSprite> findByCharacterIdAndEmotion(Long characterId, String emotion) {
    try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
      return session.createQuery("FROM GameCharacterSprite WHERE gameCharacter.id = :characterId"
          + " AND emotion = :emotion", GameCharacterSprite.class)
          .setParameter("characterId", characterId)
          .setParameter("emotion", emotion)
          .uniqueResultOptional();
    }  catch (Exception e){
      log.error("Failed to fetch GameCharacterSprite by characterId: {} and emotion: {}", characterId, emotion, e);
      throw new EntityFetchException(GameCharacterSprite.class.getSimpleName(), e);
    }
  }

  @Override
  public List<GameCharacterSprite> findByCharacterId(Long characterId) {
    try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
      return session.createQuery("FROM GameCharacterSprite WHERE gameCharacter.id = :characterId",
          GameCharacterSprite.class)
          .setParameter("characterId", characterId)
          .getResultList();
    } catch (Exception e){
      log.error("Failed to fetch GameCharacterSprites by characterId: {}", characterId, e);
      throw new EntityFetchException(GameCharacterSprite.class.getSimpleName(), e);
    }
  }
}
