-- -- 1. Персонажі
-- INSERT INTO characters (id, name, created_at, updated_at) VALUES (1, 'Haruka', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
--
-- -- 2. Емоції (спрайти)
-- INSERT INTO character_sprites (id, emotion, sprite_path, character_id, created_at, updated_at) VALUES (1, 'laugh', 'HARUKA_LAUGH', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
-- INSERT INTO character_sprites (id, emotion, sprite_path, character_id, created_at, updated_at) VALUES (2, 'dirty', 'HARUKA_DIRTY', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
--
-- -- 3. Сцени
-- INSERT INTO game_scenes (id, title, background_path, soundtrack_path, created_at, updated_at) VALUES (1, 'class', 'CLASS_DAY', 'RAINDROP_AND_PUDDLES', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
-- INSERT INTO game_scenes (id, title, background_path, soundtrack_path, created_at, updated_at) VALUES (2, 'rooftop', 'ROOFTOP_DAY', 'OST', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
--
-- -- 4. Діалоги (Scene 1)
-- INSERT INTO dialogues (id, order_index, sprite_position, text, character_id, character_sprite_id, game_scene_id, created_at, updated_at) VALUES (1, 1, 'CENTRAL', 'Привіт! Як справи, що, знайшов вже друзів?', 1, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
-- INSERT INTO dialogues (id, order_index, sprite_position, text, character_id, character_sprite_id, game_scene_id, created_at, updated_at) VALUES (2, 2, 'CENTRAL', 'Я розумію, може бути важко... Стільки нових людей..', 1, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
-- INSERT INTO dialogues (id, order_index, sprite_position, text, character_id, character_sprite_id, game_scene_id, created_at, updated_at) VALUES (3, 3, 'CENTRAL', 'Щож... А давай пройдемося!', 1, 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
-- INSERT INTO dialogues (id, order_index, sprite_position, text, character_id, character_sprite_id, game_scene_id, created_at, updated_at) VALUES (4, 4, 'LEFT', 'Яка краса!', 1, 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
-- INSERT INTO dialogues (id, order_index, sprite_position, text, character_id, character_sprite_id, game_scene_id, created_at, updated_at) VALUES (5, 5, 'RIGHT', '*Бігає зі сторони в сторону* хі-хі, як же весело!', 1, 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
--
-- -- 5. Флаги
-- INSERT INTO flags (id, name, created_at, updated_at) VALUES (1, 'acceptedWalking', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
--
-- -- 6. Вибори
-- INSERT INTO choices (id, choice_text, order_index, game_scene_id, next_game_scene_id, created_at, updated_at) VALUES (1, 'А давай!', 1, 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
-- INSERT INTO choices (id, choice_text, order_index, game_scene_id, next_game_scene_id, created_at, updated_at) VALUES (2, 'Е ні, бажання 0', 2, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
--
-- -- З флагом
-- INSERT INTO choices (id, choice_text, order_index, required_flag_value, required_flag_id, game_scene_id, next_game_scene_id, created_at, updated_at) VALUES (3, 'Ух', 1, 'true', 1,2,  1,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
-- -- 7. Наслідки вибору
-- INSERT INTO choice_effects (id, new_value, choice_id, flag_id, created_at, updated_at) VALUES (1, 'true', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
-- INSERT INTO choice_effects (id, new_value, choice_id, flag_id, created_at, updated_at) VALUES (2, 'false', 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- SCENES

INSERT INTO game_scenes (id, next_scene_id, title, background_path, soundtrack_path, created_at, updated_at) VALUES (1, 2, 'street', 'STREET_DAY', 'RAINDROP_AND_PUDDLES', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO game_scenes (id, next_scene_id, title, background_path, soundtrack_path, created_at, updated_at) VALUES (2, 3, 'class', 'CLASS_DAY', 'OST', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO game_scenes (id, title, background_path, created_at, updated_at) VALUES (3, 'class_haruka', 'CLASS_DAY', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- CHARACTERS
INSERT INTO characters (id, name, created_at, updated_at) VALUES (1, 'Haruka', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO characters (id, name, created_at, updated_at) VALUES (2, 'Aya', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO characters (id, name, created_at, updated_at) VALUES (3, 'Mio', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- CHARACTERS EMOTIONS(Sprites)
INSERT INTO character_sprites (id, emotion, sprite_path, character_id, created_at, updated_at) VALUES (1, 'laugh', 'HARUKA_LAUGH', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO character_sprites (id, emotion, sprite_path, character_id, created_at, updated_at) VALUES (2, 'dirty', 'HARUKA_DIRTY', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO character_sprites (id, emotion, sprite_path, character_id, created_at, updated_at) VALUES (3, 'happy', 'AYA_HAPPY', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO character_sprites (id, emotion, sprite_path, character_id, created_at, updated_at) VALUES (4, 'normal', 'AYA_NORMAL', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO character_sprites (id, emotion, sprite_path, character_id, created_at, updated_at) VALUES (5, 'cat_smile', 'MIO_CAT_SMILE', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO character_sprites (id, emotion, sprite_path, character_id, created_at, updated_at) VALUES (6, 'normal', 'MIO_NORMAL', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- DIALOGUES(Scene 1)
INSERT INTO dialogues (id, order_index, text, game_scene_id, created_at, updated_at) VALUES (1, 1, 'The spring breeze pleasantly cools the face.', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, text, game_scene_id, created_at, updated_at) VALUES (2, 2, 'New school year. Penultimate grade...', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, text, game_scene_id, created_at, updated_at) VALUES (3, 3, 'It would seem that this path to school has already been studied so well that I could walk it with my eyes closed.', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, text, game_scene_id, created_at, updated_at) VALUES (4, 4, 'But today everything feels a little different, I don`t know why, maybe I`m sick?', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, text, game_scene_id, created_at, updated_at) VALUES (5, 5, 'I hope there will be good people in the new class. Maybe even some of my former classmates?', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, text, game_scene_id, created_at, updated_at) VALUES (6, 6, 'Although... "classmates" is a big word. Rather, they are people whose names I remember and whom I sometimes greeted in the corridors. I never had any real friends..', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, text, game_scene_id, created_at, updated_at) VALUES (7, 7, 'Okay, stop beating yourself up. It`s just another year. No need to complicate things.', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- DIALOGUES(Scene 2)
INSERT INTO dialogues (id, order_index, text, game_scene_id, created_at, updated_at) VALUES (8, 1, 'I entered the classroom and quickly looked around, it seemed like I didn`t know anyone at all... Trouble...', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, text, game_scene_id, created_at, updated_at) VALUES (9, 2, 'I put my things on the desk, and before I could even sit down, a familiar voice called out to me.', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- DIALOGUES(Scene 3)
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, text, game_scene_id, created_at, updated_at) VALUES (10, 1, 'CENTRAL', 1, 1, 'Hey! Long time no see!', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, text, game_scene_id, created_at, updated_at) VALUES (11, 2, 'CENTRAL', 1, 1, 'It`s me, Haruka. We were in the same class last year, remember?', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, text, game_scene_id, created_at, updated_at) VALUES (12, 3, 'Haruka... Yeah, of course. The student council president. Hard to forget.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, text, game_scene_id, created_at, updated_at) VALUES (13, 4, 'CENTRAL', 1, 2, 'He-he~ It`s nice to see a familiar face. Honestly, I wasn`t expecting to know anyone here.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, text, game_scene_id, created_at, updated_at) VALUES (14, 5, 'Same. The whole class feels like strangers. Strange for the same year level.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, text, game_scene_id, created_at, updated_at) VALUES (15, 6, 'CENTRAL', 1, 1, 'Well, if I had to memorize everyone I`ve ever passed in the hallway, I`d have already lost my mind.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, text, game_scene_id, created_at, updated_at) VALUES (16, 7, 'At that moment, a girl quietly approaches. I didn`t even notice her until she was already standing there.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, text, game_scene_id, created_at, updated_at) VALUES (17, 8, 'CENTRAL', 1, 1, 'Oh, Mio! Good morning! Same class again this year!', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, text, game_scene_id, created_at, updated_at) VALUES (18, 9, 'RIGHT', 3, 6, '...Morning, Haruka. (Her gaze slowly drifts toward me, studying my face in silence.)', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, text, game_scene_id, created_at, updated_at) VALUES (19, 10, 'CENTRAL', 1, 1, 'Ah, right — introductions! We were classmates in first year. And this is Mio.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, text, game_scene_id, created_at, updated_at) VALUES (20, 11, 'RIGHT', 3, 6, '...Mio. Nice to meet you. (Her voice is barely audible over the noise of the classroom.)', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, text, game_scene_id, created_at, updated_at) VALUES (21, 12, 'N-nice to meet you too. I didn`t expect to run into so many people on the first day.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, text, game_scene_id, created_at, updated_at) VALUES (22, 13, 'RIGHT', 3, 5, '...You two are loud. (She gives the faintest smile, takes a seat nearby, and puts in one earbud.)', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, text, game_scene_id, created_at, updated_at) VALUES (23, 14, 'CENTRAL', 1, 1, 'Ahaha, that`s just Mio for you. She`d rather watch people than talk to them.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, text, game_scene_id, created_at, updated_at) VALUES (24, 15, 'I glance around the room again. The tension has eased a little. Then, near the window in the far corner — another girl catches my eye.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, text, game_scene_id, created_at, updated_at) VALUES (25, 16, 'She`s sitting perfectly still, a thick book open in her hands. She looks completely detached from everything around her.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, text, game_scene_id, created_at, updated_at) VALUES (26, 17, 'Haruka, who is that girl reading over there?', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, text, game_scene_id, created_at, updated_at) VALUES (27, 18, 'CENTRAL', 1, 1, 'Oh! That`s Aya. We`ve been friends for a while. Come on, I`ll introduce you!', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, text, game_scene_id, created_at, updated_at) VALUES (28, 19, 'Before I could even hesitate, Haruka grabs my sleeve and drags me toward the back of the classroom.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, text, game_scene_id, created_at, updated_at) VALUES (29, 20, 'CENTRAL', 1, 1, 'Aya~ Earth to Aya! We`re in the same class again!', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, text, game_scene_id, created_at, updated_at) VALUES (30, 21, 'LEFT', 2, 4, '...Ah. Haruka... I didn`t notice you. The classroom is so loud... it feels like a storm.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, text, game_scene_id, created_at, updated_at) VALUES (31, 22, 'CENTRAL', 1, 1, 'Always in your own world! This is my old classmate. We all ended up together!', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, text, game_scene_id, created_at, updated_at) VALUES (32, 23, 'LEFT', 2, 4, '...Hello. I am Aya... Nice to meet you.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, text, game_scene_id, created_at, updated_at) VALUES (33, 24, 'Nice to meet you too. What are you reading?', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, text, game_scene_id, created_at, updated_at) VALUES (34, 25, 'LEFT', 2, 3, '...A story about the end of the world. It is quite peaceful... watching everything slowly fade away into silence.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, text, game_scene_id, created_at, updated_at) VALUES (35, 26, 'I wasn`t quite sure how to respond to that. She speaks so softly, yet her words carry a strange, melancholic weight.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, text, game_scene_id, created_at, updated_at) VALUES (36, 27, 'LEFT', 2, 4, '...Do you think... a flower is most beautiful right before it withers?', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);