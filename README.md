# 🥨 jeonghyeon-backend-magazine 🥨
http://13.209.40.211/

임시 CORS 열어둔 주소: http://localhost:3000

## API List

## 1. 게시글 작성
  ✔︎ Method: POST
  
  ✔︎ Api: /api/post
  
  ✔︎ Request: 
  
              {String "imageUrl":"imageUrl" ,
  
              String "contents":"contents", 
              
              String "layoutType":"layoutType"}    (DEFAULT, LEFT, RIGHT)
              
  ✔︎ Response: Long 저장한 postId
              
              // 로그인하지 않은 사용자의 경우 400에러와 메시지("로그인하지 않은 사용자는 포스팅할 수 없습니다.")
              // layoutType은 우선 (DEFAULT, LEFT, RIGHT) 중 하나로 설정되어 있습니다. 그 외 문자열 입력시 400에러가 나옵니다.
              
## 2. 단일 게시글 조회              
  ✔︎ Method: GET
  
  ✔︎ Api: /api/post/{postId}
  
  ✔︎ Request: Long postId
  
  ✔︎ Response: 
                          
              PostToFE = {Long "postId": "postId",                  (PostToFE: 프론트엔드로 보내기 위해 재구성한 Post정보모음클래스)
  
                          String "nickname": "nickname",
                          
                          String "createdAt": "createdAt",
                          
                          String "contents": "contents",
                          
                          String "imageUrl": "imageUrl",
                          
                          Long "likeCnt": "likeCnt",
                          
                          Boolean "userLiked"; "userLiked"          (현재 로그인한 사용자가 해당 게시글을 좋아요했는지 여부 ex. true, false)
                          
                          String "layoutType"; "layoutType"}        (DEFAULT, LEFT, RIGHT)
              
## 3. 전체 게시글 조회
  ✔︎ Method: GET
  
  ✔︎ Api: /api/post
  
  ✔︎ Request: 없음 또는 페이징을 위한 경로변수 (ex. /api/post?page=0&size=3)
  
  ✔︎ Response: PostToFE 의 List 
  
  
## 4. 게시글 수정
  ✔︎ Method: PUT
  
  ✔︎ Api: /api/post/{postId}
  
  ✔︎ Request: 
            
             {String "imageUrl": "imageUrl" ,
  
              String "contents": "contents", 
  
              String "layoutType": "layoutType"}   (DEFAULT, LEFT, RIGHT)
  
  ✔︎ Response: Long 수정한 postId
  
              // 로그인하지 않은 사용자의 경우 400에러와 메시지("로그인이 필요합니다.")
              // 작성자가 아닌 사용자의 경우 400에러와 메시지("작성자가 아니면 수정할 수 없습니다.")
  
## 5. 게시글 삭제
  ✔︎ Method: DELETE
  
  ✔︎ Api: /api/post/{postId}/like
  
  ✔︎ Request: Long postId
  
  ✔︎ Response: Long 삭제한 postId
  
               // 로그인하지 않은 사용자의 경우 400에러와 메시지("로그인이 필요합니다.")
               // 작성자가 아닌 사용자의 경우 400에러와 메시지("작성자가 아니면 삭제할 수 없습니다.")
  
## 6. 좋아요 변화
  ✔︎ Method: GET
  
  ✔︎ Api: /api/post/{postId}/like
  
  ✔︎ Request: Long postId
  
  ✔︎ Response: String "좋아요 추가 완료 or 좋아요 취소 완료" 
  
               // 로그인하지 않은 사용자의 경우 400에러와 메시지("좋아요를 하기 위해서는 로그인이 필요합니다.")
               
## 7. 회원가입
  ✔︎ Api: /user/signup
  
  ✔︎ Request: Form data = 
  
                        {String "userEmail":"userEmail",
  
                          String "password":"password",
  
                          String "nickname":"nickname"}
  
  ✔︎ Response: Success -> 로그인(/user/login)으로 redirect
  
              // 다음의 경우에 400에러와 메시지 : 중복 이메일 존재, 중복 닉네임 존재, 패스워드에 닉네임 포함, 닉네임이 3글자 미만, 닉네임에 특수문자나 한글 포함
              
## 8. 로그인
  ✔︎ Api: /user/login
  
  ✔︎ Request: Form data = 
  
                        {String "username":"userEmail(실제로는 이메일값인데, name부분만 username이에요..^^ 죄송합니다!!)",
  
                          String "password":"password"}
  
  ✔︎ Response: Success
  
              // 이메일 또는 비밀번호 불일치시 재확인 요구하는 400
  
  
