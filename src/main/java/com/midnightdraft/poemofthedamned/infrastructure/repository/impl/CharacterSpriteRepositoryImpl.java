package com.midnightdraft.poemofthedamned.infrastructure.repository.impl;

import com.midnightdraft.poemofthedamned.domain.model.CharacterSprite;
import com.midnightdraft.poemofthedamned.infrastructure.exception.RepositoryException.EntityFetchException;
import com.midnightdraft.poemofthedamned.infrastructure.repository.CharacterSpriteRepository;
import com.midnightdraft.poemofthedamned.infrastructure.util.HibernateSessionFactory;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;

public class CharacterSpriteRepositoryImpl extends BaseRepositoryImpl<CharacterSprite> implements
    CharacterSpriteRepository {

  public CharacterSpriteRepositoryImpl(){
    super(CharacterSprite.class);
  }

  @Override
  public Optional<CharacterSprite> findByCharacterIdAndEmotion(Long characterId, String emotion) {
    try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
      return session.createQuery("FROM CharacterSprite WHERE character.id = :characterId"
          + " AND emotion = :emotion", CharacterSprite.class)
          .setParameter("characterId", characterId)
          .setParameter("emotion", emotion)
          .uniqueResultOptional();
    }  catch (Exception e){
      throw new EntityFetchException(CharacterSprite.class.getSimpleName(), e);
    }
  }

  @Override
  public List<CharacterSprite> findByCharacterId(Long characterId) {
    try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
      return session.createQuery("FROM CharacterSprite WHERE character.id = :characterId",
          CharacterSprite.class)
          .setParameter("characterId", characterId)
          .getResultList();
    } catch (Exception e){
      throw new EntityFetchException(CharacterSprite.class.getSimpleName(), e);
    }
  }
}
