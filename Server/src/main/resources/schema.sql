
drop all objects;
CREATE TABLE `dic`(
`id` int PRIMARY KEY AUTO_INCREMENT,
`name` varchar(200),
`score` int



);
CREATE TABLE `member` (
  `member_id` int PRIMARY KEY AUTO_INCREMENT,
  `password` varchar(255),
  `nickname` varchar(255),
  `id` varchar(255),
  `profile_id` varchar(255),
  --`post_id` int,
  `comment_id` int,
  `role` varchar(50),
  `sign_in_date` datetime,
  `ProfileImage` varchar(200)
  --`member_post_id` int,

);

CREATE TABLE `post` (
  `post_id` int PRIMARY KEY AUTO_INCREMENT,
  `post_content` varchar(255),
  --`member_id` int,
  `post_name` varchar(255),
  `write_date` TIMESTAMP,
  `modified_date` datetime,
  `view_count` int,
  --`comment_id` int,
  --`answer_id` int,
  `is_answered` boolean,
  --`member_post_id` int,
  `score` int
  --`post_vote_id` int,
  --`tag` int
);

CREATE TABLE `member_post`(
`member_post_id` int PRIMARY KEY AUTO_INCREMENT,
`member_id` int,
`post_id` int

);

CREATE TABLE `comment` (
  `comment_id` int PRIMARY KEY AUTO_INCREMENT,
  `comment_content` varchar(255),
  `comment_date` TIMESTAMP,
  `comment_modified_date` TIMESTAMP,
  `member_id` int,
  `post_id` int,
  `is_updated` boolean,
  `answer_id` int
);

CREATE TABLE `answer` (
  `answer_id` int PRIMARY KEY AUTO_INCREMENT,
  `answer_content` varchar(255),
  `member_id` int,
  `write_date` datetime,
  `modified_date` datetime,
  --`is_accepted` boolean,
  `score` int,
  `answer_vote_id` int,
  `post_id` int,
  `accepted` boolean
);

CREATE TABLE `postVote` (
  `post_vote_id` int PRIMARY KEY AUTO_INCREMENT,
  `member_id` int,
  `post_id` int,
  `score` int
);

CREATE TABLE `answer_Vote` (
  `answer_vote_id` int PRIMARY KEY AUTO_INCREMENT,
  `answer_id` int,
  `member_id` int,
  `score` int
);

CREATE TABLE `tag` (
  `tag_id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `content` varchar(255),
  `questions` int
  --`post` int
);

CREATE TABLE `post_tag`(
`post_tag_id` int PRIMARY KEY AUTO_INCREMENT,
`tag_id` int,
`post_id` int

);

CREATE TABLE `logout` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `token` varchar(255)
);

CREATE TABLE `login` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `token` varchar(255)
);

CREATE TABLE `profile` (
  `profile_id` int PRIMARY KEY AUTO_INCREMENT,
 -- `member_id` int,
  `stub_reputation` int,
  `stub_reached` int,
  `profile_image_link` varchar(255),
  `about` varchar(255),
  `sign_in_date` datetime,
  `last_visit` datetime,
  `location` varchar(50),
  `displayname` varchar(50),
  `answers` int default 100
);

--ALTER TABLE `post` ADD FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`);

ALTER TABLE `comment` ADD FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`);

ALTER TABLE `answer` ADD FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`);

--ALTER TABLE `profile` ADD FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`);

ALTER TABLE `member` ADD FOREIGN KEY (`profile_id`) REFERENCES `profile` (`profile_id`);


ALTER TABLE `postVote` ADD FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`);

ALTER TABLE `answer_Vote` ADD FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`);

ALTER TABLE `postVote` ADD FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`);

ALTER TABLE `answer_Vote` ADD FOREIGN KEY (`answer_id`) REFERENCES `answer` (`answer_id`);

ALTER TABLE `comment` ADD FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`);

ALTER TABLE `comment` ADD FOREIGN KEY (`answer_id`) REFERENCES `answer` (`answer_id`);

ALTER TABLE `answer` ADD FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`);

ALTER TABLE `answer` ADD FOREIGN KEY (`answer_vote_id`) REFERENCES `answer_Vote` (`answer_vote_id`);

ALTER TABLE `member` ADD FOREIGN KEY (`comment_id`) REFERENCES `comment` (`comment_id`);

ALTER TABLE `member_post` ADD FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`);
ALTER TABLE `member_post` ADD FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`);

ALTER TABLE `post_tag` ADD FOREIGN KEY (`tag_id`) REFERENCES `tag` (`tag_id`);
ALTER TABLE `post_tag` ADD FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`);


--ALTER TABLE `post` ADD FOREIGN KEY (`tag`) REFERENCES `tag` (`id`);
--ALTER TABLE `tag` ADD FOREIGN KEY (`post`) REFERENCES `post` (`post_id`);