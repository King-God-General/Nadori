use nadori;

-- 안전 모드 비활성화
SET SQL_SAFE_UPDATES = 0;

-- content_id를 attraction_id로 이름을 변경해준다.
-- text 타입은 인덱스로 사용할 수 없기 때문에 타입 변경
ALTER TABLE attraction CHANGE content_id attraction_id varchar(12);

-- primary key로 지정한다.
ALTER TABLE attraction
ADD CONSTRAINT pk_attraction PRIMARY KEY (attraction_id);

-- 모든 id가 unique한지 확인해준다.
select count(*), count(distinct attraction_id) from attraction;

-- 나중에 인덱스로 쓰일 법한 애들도 타입을 변경해준다.
ALTER TABLE attraction CHANGE cat1 cat1 varchar(3);
ALTER TABLE attraction CHANGE cat2 cat2 varchar(6);
ALTER TABLE attraction CHANGE cat3 cat3 varchar(10);
ALTER TABLE attraction CHANGE zipcode zipcode varchar(10);
ALTER TABLE attraction CHANGE addr1 addr1 varchar(100);
ALTER TABLE attraction CHANGE addr2 addr2 varchar(100);
ALTER TABLE attraction CHANGE first_image first_image varchar(100);
ALTER TABLE attraction CHANGE first_image2 first_image2 varchar(100);

-- 안전 모드 다시 활성화
SET SQL_SAFE_UPDATES = 1;

-- 기존 테이블 삭제 순서 조정
drop table if exists content;
drop table if exists plan;
drop table if exists member;

-- member 테이블 생성
create table member (
    member_id int primary key auto_increment,
    email varchar(20) not null,
    password varchar(20) not null,
    username varchar(20) not null,
    gender boolean,
    birthdate datetime
);

-- plan 테이블 생성
create table plan (
    plan_id int primary key auto_increment,
    member_id int not null,
    title varchar(100) not null,
    description varchar(500),
    startDate datetime not null,
    endDate datetime not null,
    foreign key (member_id) references member(member_id)
);

-- content 테이블 생성
create table content (
    content_id int primary key auto_increment,
    plan_id int not null,
    dayNum int not null,
    contentNum int not null,
    memo varchar(500),
    attraction_id varchar(12),
    foreign key (plan_id) references plan(plan_id),
    foreign key (attraction_id) references attraction(attraction_id)
);

select * from attraction where title like "%한라산%";

commit;

