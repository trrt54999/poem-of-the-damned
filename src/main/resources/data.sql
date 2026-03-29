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
INSERT INTO game_scenes (id, next_scene_id, title, background_path, soundtrack_path, ambient_path, created_at, updated_at) VALUES (3, 4, 'class_haruka', 'CLASS_DAY', 'OST', 'FADE_OUT',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO game_scenes (id, next_scene_id, title, background_path, soundtrack_path, ambient_path,created_at, updated_at) VALUES (4, 5, 'rooftop', 'ROOFTOP_DAY', 'OST', 'FADE_OUT',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO game_scenes (id, next_scene_id, title, background_path, soundtrack_path, created_at, updated_at) VALUES (5, 6, 'class_eve', 'CLASS_EVE', 'OST', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO game_scenes (id, next_scene_id, title, background_path, soundtrack_path, created_at, updated_at) VALUES (6, 7, 'street_eve', 'STREET_EVE', 'RAINDROP_AND_PUDDLES', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO game_scenes (id, title, background_path, created_at, updated_at) VALUES (7, 'room_night', 'ROOM_NIGHT', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

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
INSERT INTO character_sprites (id, emotion, sprite_path, character_id, created_at, updated_at) VALUES (7, 'curious', 'AYA_CURIOUS', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO character_sprites (id, emotion, sprite_path, character_id, created_at, updated_at) VALUES (8, 'smile', 'HARUKA_SMILE', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO character_sprites (id, emotion, sprite_path, character_id, created_at, updated_at) VALUES (9, 'annoyed', 'HARUKA_ANNOYED', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- DIALOGUES(Scene 1)
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (1, 1, 'The spring breeze pleasantly cools the face.', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (2, 2, 'New school year. Penultimate grade...', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (3, 3, 'It would seem that this path to school has already been studied so well that I could walk it with my eyes closed.', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (4, 4, 'But today everything feels a little different, I don`t know why, maybe I`m sick?', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (5, 5, 'I hope there will be good people in the new class. Maybe even some of my former classmates?', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (6, 6, 'Although... "classmates" is a big word. Rather, they are people whose names I remember and whom I sometimes greeted in the corridors. I never had any real friends..', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (7, 7, 'Okay, stop beating yourself up. It`s just another year. No need to complicate things.', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- DIALOGUES(Scene 2)
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, ambient_path, created_at, updated_at) VALUES (8, 1, 'I entered the classroom and quickly looked around, it seemed like I didn`t know anyone at all... Trouble...', 2, 'SCHOOL_HALLWAY', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (9, 2, 'I put my things on the desk, and before I could even sit down, a familiar voice called out to me.', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- DIALOGUES(Scene 3)
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, default_text, game_scene_id, created_at, updated_at) VALUES (10, 1, 'CENTRAL', 1, 1, 'Hey! Long time no see!', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, default_text, game_scene_id, created_at, updated_at) VALUES (11, 2, 'CENTRAL', 1, 1, 'It`s me, Haruka. We were in the same class last year, remember?', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (12, 3, 'Haruka... Yeah, of course. The student council president. Hard to forget.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, default_text, game_scene_id, created_at, updated_at) VALUES (13, 4, 'CENTRAL', 1, 2, 'He-he~ It`s nice to see a familiar face. Honestly, I wasn`t expecting to know anyone here.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (14, 5, 'Same. The whole class feels like strangers. Strange for the same year level.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, default_text, game_scene_id, created_at, updated_at) VALUES (15, 6, 'CENTRAL', 1, 1, 'Well, if I had to memorize everyone I`ve ever passed in the hallway, I`d have already lost my mind.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (16, 7, 'At that moment, a girl quietly approaches. I didn`t even notice her until she was already standing there.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, default_text, game_scene_id, created_at, updated_at) VALUES (17, 8, 'CENTRAL', 1, 1, 'Oh, Mio! Good morning! Same class again this year!', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, default_text, game_scene_id, created_at, updated_at) VALUES (18, 9, 'RIGHT', 3, 6, '...Morning, Haruka. (Her gaze slowly drifts toward me, studying my face in silence.)', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, default_text, game_scene_id, created_at, updated_at) VALUES (19, 10, 'CENTRAL', 1, 1, 'Ah, right — introductions! We were classmates in first year. And this is Mio.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, default_text, game_scene_id, created_at, updated_at) VALUES (20, 11, 'RIGHT', 3, 6, '...Mio. Nice to meet you. (Her voice is barely audible over the noise of the classroom.)', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (21, 12, 'N-nice to meet you too. I didn`t expect to run into so many people on the first day.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, default_text, game_scene_id, created_at, updated_at) VALUES (22, 13, 'RIGHT', 3, 5, '...You two are loud. (She gives the faintest smile, takes a seat nearby, and puts in one earbud.)', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, default_text, game_scene_id, created_at, updated_at) VALUES (23, 14, 'CENTRAL', 1, 1, 'Ahaha, that`s just Mio for you. She`d rather watch people than talk to them.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (24, 15, 'I glance around the room again. The tension has eased a little. Then, near the window in the far corner — another girl catches my eye.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (25, 16, 'She`s sitting perfectly still, a thick book open in her hands. She looks completely detached from everything around her.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (26, 17, 'Haruka, who is that girl reading over there?', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, default_text, game_scene_id, created_at, updated_at) VALUES (27, 18, 'CENTRAL', 1, 1, 'Oh! That`s Aya. We`ve been friends for a while. Come on, I`ll introduce you!', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (28, 19, 'Before I could even hesitate, Haruka grabs my sleeve and drags me toward the back of the classroom.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, default_text, game_scene_id, created_at, updated_at) VALUES (29, 20, 'CENTRAL', 1, 1, 'Aya~ Earth to Aya! We`re in the same class again!', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, default_text, game_scene_id, created_at, updated_at) VALUES (30, 21, 'LEFT', 2, 4, '...Ah. Haruka... I didn`t notice you. The classroom is so loud... it feels like a storm.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, default_text, game_scene_id, created_at, updated_at) VALUES (31, 22, 'CENTRAL', 1, 1, 'Always in your own world! This is my old classmate. We all ended up together!', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, default_text, game_scene_id, created_at, updated_at) VALUES (32, 23, 'LEFT', 2, 4, '...Hello. I am Aya... Nice to meet you.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (33, 24, 'Nice to meet you too. What are you reading?', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, default_text, game_scene_id, created_at, updated_at) VALUES (34, 25, 'LEFT', 2, 3, '...A story about the end of the world. It is quite peaceful... watching everything slowly fade away into silence.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (35, 26, 'I wasn`t quite sure how to respond to that. She speaks so softly, yet her words carry a strange, melancholic weight.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, default_text, game_scene_id, created_at, updated_at) VALUES (36, 27, 'LEFT', 2, 4, '...Do you think... a flower is most beautiful right before it withers?', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (37, 28, 'Emm...', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, default_text, game_scene_id, created_at, updated_at) VALUES (38, 29, 'LEFT', 2, 7, 'Never mind...', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (39, 30, 'She`s... rather... strange.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (40, 31, 'She takes her seat, leaving me one-on-one with Haruka.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (41, 32, 'You have... fun friends. This semester definitely won`t be boring.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, default_text, game_scene_id, created_at, updated_at) VALUES (42, 33, 'CENTRAL', 1, 1, 'Yep!', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, music_path, ambient_path, created_at, updated_at) VALUES (43, 34, 'The bell rang and everyone took their seats, but I couldn`t focus on studying. I was thinking about how I was going to get along with my new classmates...', 3, 'FADE_OUT','SCHOOL_BELL', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (44, 35, 'Looks like sitting quietly in the corner won`t work this time...', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (45, 36, 'And so, with my head in the clouds, I sat through the entire lesson...', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- DIALOGUES (Scene 4 - Rooftop)
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (46, 1, 'After class, I decided to take a walk on the roof. I liked eating lunch here, but even here I wasn`t left in peace...', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, default_text, game_scene_id, created_at, updated_at) VALUES (47, 2, 'CENTRAL', 1, 1, 'Oh, hello again!', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (48, 3, 'Whoa! Haruka, you scared me! What are you doing here??', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, default_text, game_scene_id, created_at, updated_at) VALUES (49, 4, 'CENTRAL', 1, 1, 'Hahaha! Sorry, it`s pretty boring in the student council, so I decided to take a walk...', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, default_text, game_scene_id, created_at, updated_at) VALUES (50, 5, 'CENTRAL', 1, 8, 'By the way, did you talk to anyone from class after the lesson?', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (51, 6, 'Umm... you know, I`m not really into making friends...', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, default_text, game_scene_id, created_at, updated_at) VALUES (52, 7, 'CENTRAL', 1, 9, 'Whaaat??? Yeah, we need to fix that...', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (53, 8, 'I just said, I`m not really into it...', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, default_text, game_scene_id, created_at, updated_at) VALUES (54, 9, 'CENTRAL', 1, 1, 'Ha! I`m not taking no for an answer! Let`s go, I`ll show you my friends` clubs!', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (55, 10, 'Let`s not do this today, we won`t have time anyway...', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, default_text, game_scene_id, created_at, updated_at) VALUES (56, 11, 'CENTRAL', 1, 2, 'Okay, but tomorrow you won`t escape me!', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (57, 12, 'Fine, fine... see ya.', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, character_id, character_sprite_id, default_text, game_scene_id, created_at, updated_at) VALUES (58, 13, 'CENTRAL', 1, 1, 'See you tomorrow! I`ve got my eye on you!', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (59, 14, 'Was she always this energetic? I used to think the student council president was all about rules and boredom. But she acts like we`re... childhood friends.', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- DIALOGUES (Scene 5 - Class Evening)
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (60, 1, 'Classes are finally over. The setting sun bathed the empty classroom in thick orange light.', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (61, 2, 'I slowly packed my textbooks into my bag, enjoying this sudden silence after a noisy day.', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (62, 3, 'Aya, Mio... and Haruka.', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (63, 4, 'Aya seems so fragile. Like if you speak just a bit too loudly, she`ll shatter like porcelain. And those words of hers about withered flowers...', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (64, 5, 'And Mio... She didn`t even look at me properly. Just evaluated me. As if she was listening to my heartbeat, deciding whether it was too loud.', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (65, 6, 'Suddenly, my gaze fell on Haruka`s desk.', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (66, 7, 'Strange. She left first, supposedly because of student council business, but her notebook and pen are still lying here.', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (67, 8, 'She was always so put together... Maybe she just forgot?', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (68, 9, 'Never mind... I decided not to pry into other people`s business, slung my bag over my shoulder, and left the classroom.', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- DIALOGUES (Scene 6 - Street Evening)
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (69, 1, 'The evening air was pleasantly cool. The streetlights were already turning on.', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (70, 2, 'Usually, the first day of school blends into one gray blur. But today... today everything felt too vivid. Too much attention on me.', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (71, 3, 'I feel like the main character of some manga... hah, funny.', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- DIALOGUES (Scene 7 - Room Night)
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (72, 1, 'I collapsed onto the bed without even changing clothes. Exhaustion crashed down on me with all its weight.', 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (73, 2, 'Finally home. It`s quiet here. It`s safe here.', 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (74, 3, 'Tomorrow Haruka promised to drag me around the clubs... The music club, where that silent Mio hangs out. And the literature club, where Aya reads her depressing books.', 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (75, 4, 'Where would I rather go?', 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (76, 5, 'Honestly, nowhere. But it seems I won`t have a choice. Haruka isn`t the type to take no for an answer.', 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, default_text, game_scene_id, created_at, updated_at) VALUES (77, 6, 'Tomorrow will be a long day. I just need to sleep. Just sleep...', 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);



--- LOCALIZATION UKRAINIAN
INSERT INTO character_translations (character_id, locale, translated_name) VALUES (1, 'uk', 'Харука');

INSERT INTO dialogue_translations (dialogue_id, locale, translated_text) VALUES (1, 'uk', 'Весняний вітерець приємно холодить обличчя.');
INSERT INTO dialogue_translations (dialogue_id, locale, translated_text) VALUES (2, 'uk', 'Новий навчальний рік. Передостанній клас...');