INSERT INTO USERS VALUES('fftl');

INSERT INTO VOTE(vote_id, board_id, title, description, dead_line, user_id) VALUES(
'abcd', 1, '제목입니다.', '찬성 혹은 반대?', '2021-12-30 20:00:01', 'fftl');

INSERT INTO VOTE_ITEM(vote_item_id, content, cnt, vote_id) VALUES(1, '찬성', 0, 'abcd');
INSERT INTO VOTE_ITEM(vote_item_id, content, cnt, vote_id) VALUES(2, '반대', 0, 'abcd');

