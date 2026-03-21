-- 1. Персонажі
INSERT INTO characters (id, name, created_at, updated_at) VALUES (1, 'Haruka', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 2. Емоції (спрайти)
INSERT INTO character_sprites (id, emotion, sprite_path, character_id, created_at, updated_at) VALUES (1, 'laugh', 'HARUKA_LAUGH', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO character_sprites (id, emotion, sprite_path, character_id, created_at, updated_at) VALUES (2, 'dirty', 'HARUKA_DIRTY', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 3. Сцени
INSERT INTO game_scenes (id, title, background_path, soundtrack_path, created_at, updated_at) VALUES (1, 'class', 'CLASS_DAY', 'RAINDROP_AND_PUDDLES', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO game_scenes (id, title, background_path, soundtrack_path, created_at, updated_at) VALUES (2, 'rooftop', 'ROOFTOP_DAY', 'OST', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 4. Діалоги (Scene 1)
INSERT INTO dialogues (id, order_index, sprite_position, text, character_id, character_sprite_id, game_scene_id, created_at, updated_at) VALUES (1, 1, 'CENTRAL', 'Привіт! Як справи, що, знайшов вже друзів?', 1, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, text, character_id, character_sprite_id, game_scene_id, created_at, updated_at) VALUES (2, 2, 'CENTRAL', 'Я розумію, може бути важко... Стільки нових людей..', 1, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, text, character_id, character_sprite_id, game_scene_id, created_at, updated_at) VALUES (3, 3, 'CENTRAL', 'Щож... А давай пройдемося!', 1, 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, text, character_id, character_sprite_id, game_scene_id, created_at, updated_at) VALUES (4, 4, 'CENTRAL', 'Яка краса!', 1, 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO dialogues (id, order_index, sprite_position, text, character_id, character_sprite_id, game_scene_id, created_at, updated_at) VALUES (5, 5, 'CENTRAL', '*Бігає зі сторони в сторону* хі-хі, як же весело!', 1, 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 5. Флаги
INSERT INTO flags (id, name, created_at, updated_at) VALUES (1, 'acceptedWalking', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 6. Вибори
INSERT INTO choices (id, choice_text, order_index, game_scene_id, next_game_scene_id, created_at, updated_at) VALUES (1, 'А давай!', 1, 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO choices (id, choice_text, order_index, game_scene_id, next_game_scene_id, created_at, updated_at) VALUES (2, 'Е ні, бажання 0', 2, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- З флагом
INSERT INTO choices (id, choice_text, order_index, required_flag_value, required_flag_id, game_scene_id, next_game_scene_id, created_at, updated_at) VALUES (3, 'Ух', 1, 'true', 1,2,  1,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
-- 7. Наслідки вибору
INSERT INTO choice_effects (id, new_value, choice_id, flag_id, created_at, updated_at) VALUES (1, 'true', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO choice_effects (id, new_value, choice_id, flag_id, created_at, updated_at) VALUES (2, 'false', 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);