use nadori;

-- 안전 모드 비활성화
SET SQL_SAFE_UPDATES = 0;

-- content 테이블의 모든 데이터 삭제
delete from content;
-- plan 테이블의 모든 데이터 삭제
delete from plan;
-- member 테이블의 모든 데이터 삭제
delete from member;

-- 안전 모드 다시 활성화
SET SQL_SAFE_UPDATES = 1;

-- 더미 데이터 삽입을 위한 기본 데이터
-- member 테이블에 더미 데이터 삽입
insert into member (member_id, nickname, username) values
('1', 'King', 'KingUser'),
('2', 'God', 'GodUser'),
('3', 'General', 'GeneralUser');

-- plan 테이블에 더미 데이터 삽입
insert into plan (plan_id, title, description, startDate, endDate, member_id) values
(1, '제주도', '자바자바 MT 가보자고', '2024-06-01', '2024-06-04', '1'),
(2, '서울', '', '2024-07-30', '2024-08-06', '1'),
(3, '대전', '빵순이들의 빵집 터는 여행', '2024-08-01', '2024-08-02', '3');

-- content 테이블에 더미 데이터 삽입
INSERT INTO content (content_id, plan_id, dayNum, contentNum, memo, attraction_id) VALUES
(1, 1, 1, 1, '오전 11시 비행기니까 적어도 9시까지는 공항 도착해야 함!!!', NULL),
(2, 1, 1, 2, NULL, '127635'),
(3, 1, 1, 3, '한', NULL),
(5, 1, 2, 1, NULL, '127635'),
(8, 1, 2, 2, '라', NULL),
(4, 1, 3, 1, '산', NULL),
(6, 1, 4, 1, NULL, '127635'),
(7, 1, 4, 2, '오후 8시 비행기니까 그냥 공항에서 저녁 먹기', NULL),
(10, 3, 1, 1, NULL, '2582346'),
(11, 3, 1, 2, '일단 밥부터 먹는다', NULL),
(9, 3, 2, 1, '집에 간드아아ㅏ아악 가기 싫억', NULL);

select * from attraction;
select * from member;
select * from plan;
select * from  content;

delete from content where plan_id between 28 and 42;
delete from plan where plan_id between 28 and 42;

commit;