--
-- MEMBER
--
INSERT INTO MEMBER
        (MEMBER_NO, MEMBER_ID, MEMBER_NAME, NICKNAME, MEMBER_IS_ADMIN)
VALUES
        (1, 'thewayhj', 'heejoong', 'theway_hj', 1),
        (2, 'musinsa01', '무신사', 'mu_sin_sa', 0)
;

--
-- CATEGORY_MAIN
--
INSERT INTO CATEGORY_MAIN
(CATEGORY_ID, CATEGORY_NAME)
VALUES
    (1, '상의'),
    (2, '아우터'),
    (3, '바지'),
    (4, '신발'),
    (5, '가방')
;

--
-- CATEGORY_SUB
--
INSERT INTO CATEGORY_SUB
(CATEGORY_ID, CATEGORY_NAME)
VALUES
    (1,     '반소매 티셔츠'),
    (2,     '긴소매 티셔츠'),
    (3,     '후드 집업'),
    (4,     '블루종/MA-1'),
    (5,     '데님 팬츠'),
    (6,     '코튼 팬츠'),
    (7,     '구두'),
    (8,     '로퍼'),
    (9,     '백팩'),
    (10,    '토트백')
;

--
-- CATEGORY_MAPPING
--
INSERT INTO CATEGORY_MAPPING
(CATEGORY_ID, SUB_CATEGORY_ID)
VALUES
    (1, 1),
    (1, 2),
    (2, 3),
    (2, 4),
    (3, 5),
    (3, 6),
    (4, 7),
    (4, 8),
    (5, 9),
    (5, 10)
;


--
-- CATEGORY_MAPPING
--
INSERT INTO ITEM
(ITEM_NO, CATEGORY_ID, ITEM_NAME, DESCRIPTION, PRICE, BRAND_NAME)
VALUES
    (1, 1, '쿨 코트 2-PACK 티셔츠',                 '2022 COVERNAT SUMMER T-SHIRT',   32000, '커버낫'),
    (2, 1, '보드마스터 그래픽 티셔츠',                '스테이얼론 + 보드마스터',              35800 , '페플'),
    (3, 2, '베이식 긴팔 티셔츠',                    '무신사 단독 판매',                    11790, '무신사 스탠다드'),
    (4, 3, '스웻 후드 집업',                       '무신사 한정 판매',                    39000, '토피'),
    (5, 3, '후드 스웨트 집업 멜란지 그레이',           '무신사 단독 판매',                    35890, '멜란지 마스터'),
    (6, 3, '코드 후드 집업 자켓',                   '파르티멘토 22 서머 컬렉션',             49900, '파르티멘토'),
    (7, 7, '375 웰던 더비 구두 루시블랙',            'BSQT가 제작하는 신발은 특별합니다.',      47200, '비에스큐티바이클래시'),
    (8, 8, '뉴스마트로퍼',                         '무신사 한정 판매',                    137000, '로맨틱무브'),
    (9, 8, '코즈웨이 소가죽 베이직 페니 로퍼 밴타블랙',   '무신사스토어 전 상품 무료배송',          69000 , '마이애미프로젝트'),
    (10, 8, '세미 드레스 페니 로퍼',                 '무신사 단독 판매',                    109900, '무신사 스탠다드')
;