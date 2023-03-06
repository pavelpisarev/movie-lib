INSERT INTO actors (name, date_of_birth) VALUES
('Actor 1', '1963-06-09'),
('Actor 2', '1974-11-11'),
('Actor 3', '1963-12-18'),
('Actor 4', '1962-07-03'),
('Actor 5', '1971-12-26');

INSERT INTO movies (title, description) VALUES
('Movie 1','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ipsum tortor, posuere non odio id, maximus sodales urna. Morbi leo lorem, pellentesque eget dapibus in, ullamcorper vel metus.'),
('Movie 2','Nunc porttitor tellus vitae dictum facilisis. Suspendisse eu elit enim. Aliquam quis porttitor enim. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.'),
('Movie 3','Curabitur mollis ante nulla, vitae ornare dui maximus non.'),
('Movie 4','Cras at cursus velit, quis vulputate ante. Praesent ut odio vel lacus efficitur bibendum. Etiam at dui tristique massa mollis convallis. Etiam vestibulum dolor et pharetra vestibulum.'),
('Movie 5','Maecenas faucibus ex nec lectus rhoncus efficitur. Integer porttitor nulla non ipsum tristique, eget mollis est volutpat.');

INSERT INTO genres (name, description) VALUES
('Action','Associated with particular types of spectacle'),
('Adventure','Implies a narrative that is defined by a journey'),
('Animation','A film medium in which the film''s images are primarily created by computer or hand and the characters are voiced by actors.'),
('Comedy','Defined by events that are primarily intended to make the audience laugh'),
('Drama','Focused on emotions and defined by conflict'),
('Fantasy','Films defined by situations that transcend natural laws and/or by settings inside a fictional universe, with narratives that are often inspired by or involve human myths'),
('Horror','Films that seek to elicit fear or disgust in the audience for entertainment purposes');

INSERT INTO movies_actors (movie_id, actor_id) VALUES
((SELECT id FROM movies WHERE title = 'Movie 1'), (SELECT id FROM actors WHERE name = 'Actor 1')),
((SELECT id FROM movies WHERE title = 'Movie 1'), (SELECT id FROM actors WHERE name = 'Actor 2')),
((SELECT id FROM movies WHERE title = 'Movie 2'), (SELECT id FROM actors WHERE name = 'Actor 2')),
((SELECT id FROM movies WHERE title = 'Movie 3'), (SELECT id FROM actors WHERE name = 'Actor 3')),
((SELECT id FROM movies WHERE title = 'Movie 3'), (SELECT id FROM actors WHERE name = 'Actor 4')),
((SELECT id FROM movies WHERE title = 'Movie 3'), (SELECT id FROM actors WHERE name = 'Actor 5')),
((SELECT id FROM movies WHERE title = 'Movie 4'), (SELECT id FROM actors WHERE name = 'Actor 4')),
((SELECT id FROM movies WHERE title = 'Movie 5'), (SELECT id FROM actors WHERE name = 'Actor 4')),
((SELECT id FROM movies WHERE title = 'Movie 5'), (SELECT id FROM actors WHERE name = 'Actor 5'));

INSERT INTO movies_genres (movie_id, genre_id) VALUES
((SELECT id FROM movies WHERE title = 'Movie 1'), (SELECT id FROM genres WHERE name = 'Action')),
((SELECT id FROM movies WHERE title = 'Movie 1'), (SELECT id FROM genres WHERE name = 'Adventure')),
((SELECT id FROM movies WHERE title = 'Movie 2'), (SELECT id FROM genres WHERE name = 'Animation')),
((SELECT id FROM movies WHERE title = 'Movie 2'), (SELECT id FROM genres WHERE name = 'Comedy')),
((SELECT id FROM movies WHERE title = 'Movie 3'), (SELECT id FROM genres WHERE name = 'Drama')),
((SELECT id FROM movies WHERE title = 'Movie 4'), (SELECT id FROM genres WHERE name = 'Fantasy')),
((SELECT id FROM movies WHERE title = 'Movie 5'), (SELECT id FROM genres WHERE name = 'Horror'));