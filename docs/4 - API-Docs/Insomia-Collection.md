type: collection.insomnia.rest/5.0
name: online-contest
meta:
  id: wrk_8ba874457b974a519dc82b0fb37adade
  created: 1741170332917
  modified: 1741170332917
collection:
  - name: user
    meta:
      id: fld_5af3da8bc21d40278df3849e0bb81931
      created: 1741170344201
      modified: 1744135834509
      sortKey: -1741170344201
    children:
      - url: http://localhost:8082/api/v1/user/
        name: add user
        meta:
          id: req_c79b45bd90e34882967d757c2a3a5654
          created: 1741170347108
          modified: 1744137084408
          isPrivate: false
          sortKey: -1741170347108
        method: POST
        body:
          mimeType: application/json
          text: |-
            {
            	"nickname": "yamate",
            	"fullname": "Uchiha Madara",
            	"organization": "ITMO",
            	"email": "tad@gmail.com"
            }
        headers:
          - name: Content-Type
            value: application/json
          - name: User-Agent
            value: insomnia/10.3.1
        settings:
          renderRequestBody: true
          encodeUrl: true
          followRedirects: global
          cookies:
            send: true
            store: true
          rebuildPath: true
      - url: http://localhost:8082/api/v1/user/a48fb203-1b60-4da6-8975-bd7f719e994c
        name: get user
        meta:
          id: req_5af865e8145c490ab3ca65dc32c1e771
          created: 1741170359785
          modified: 1744137441282
          isPrivate: false
          sortKey: -1741170359785
        method: GET
        headers:
          - name: User-Agent
            value: insomnia/10.3.1
        settings:
          renderRequestBody: true
          encodeUrl: true
          followRedirects: global
          cookies:
            send: true
            store: true
          rebuildPath: true
      - name: delete user
        meta:
          id: req_a5dd7dc3e05148cd95f6656fe52d8cda
          created: 1741170376221
          modified: 1741170391955
          isPrivate: false
          sortKey: -1741170376221
        method: GET
        headers:
          - name: User-Agent
            value: insomnia/10.3.1
        settings:
          renderRequestBody: true
          encodeUrl: true
          followRedirects: global
          cookies:
            send: true
            store: true
          rebuildPath: true
      - name: update user
        meta:
          id: req_c85468a36c7f46188ca8491c048c759c
          created: 1741170395421
          modified: 1741170404281
          isPrivate: false
          sortKey: -1741170395421
        method: GET
        headers:
          - name: User-Agent
            value: insomnia/10.3.1
        settings:
          renderRequestBody: true
          encodeUrl: true
          followRedirects: global
          cookies:
            send: true
            store: true
          rebuildPath: true
      - url: http://localhost:8080/api/v1/user/inc-attempt/44a9a38c-e8b1-343c-8024-83b8ea5f3181
        name: inc attemp
        meta:
          id: req_a013cdc9600647a593ec93d7b506a4d2
          created: 1741175841717
          modified: 1741175985060
          isPrivate: false
          sortKey: -1741175841717
        method: GET
        headers:
          - name: User-Agent
            value: insomnia/10.3.1
        settings:
          renderRequestBody: true
          encodeUrl: true
          followRedirects: global
          cookies:
            send: true
            store: true
          rebuildPath: true
  - name: problem
    meta:
      id: fld_c5b35bd9d3664b3cabd9d4f023535756
      created: 1741177117184
      modified: 1741177117184
      sortKey: -1741177117184
    children:
      - url: http://localhost:8080/api/v1/problem/
        name: add problem
        meta:
          id: req_a6c5aa352c044257b8729347f586f204
          created: 1741177136086
          modified: 1742551491445
          isPrivate: false
          sortKey: -1741177136086
        method: POST
        body:
          mimeType: application/json
          text: |-
            {
            	"title": "p1",
            	"description": "this is description",
            	"author": "tad",
            	"difficulty": 200,
            	"tags": ["BINARY_SEARCH", "GREEDY"]
            }
        headers:
          - name: Content-Type
            value: application/json
          - name: User-Agent
            value: insomnia/10.3.1
        settings:
          renderRequestBody: true
          encodeUrl: true
          followRedirects: global
          cookies:
            send: true
            store: true
          rebuildPath: true
      - url: http://localhost:8080/api/v1/problem/names
        name: get names
        meta:
          id: req_8c0da07f55ac4981920fd5850ee8a88a
          created: 1741179608609
          modified: 1742551490126
          isPrivate: false
          sortKey: -1741179608609
        method: GET
        headers:
          - name: User-Agent
            value: insomnia/10.3.1
        settings:
          renderRequestBody: true
          encodeUrl: true
          followRedirects: global
          cookies:
            send: true
            store: true
          rebuildPath: true
  - name: runner
    meta:
      id: fld_dc279811d0ba47f9be1155623a61d33d
      created: 1741418005262
      modified: 1741418005262
      sortKey: -1741418005262
    children:
      - url: http://localhost:8000/api/v1/resources/
        name: send resource
        meta:
          id: req_f21c0f9b2f484fdf9d5c5cfd3392c52a
          created: 1742551315464
          modified: 1742731148767
          isPrivate: false
          sortKey: -1742551315464
        method: POST
        body:
          mimeType: application/json
          text: >-
            {
            	"problem_id": "ac43f368-30ff-4831-af65-85d2c1174d00",
            	"list_images": [
            		{
            			"image_name": "python:3.9-slim",
            			"command": "sh -c 'pip install -r requirements.txt && uvicorn app.main:app --host 0.0.0.0 --port 80'",
            			"user": "root"
            		}
            	]

            	
            }
        headers:
          - name: Content-Type
            value: application/json
          - name: User-Agent
            value: insomnia/11.0.0
        settings:
          renderRequestBody: true
          encodeUrl: true
          followRedirects: global
          cookies:
            send: true
            store: true
          rebuildPath: true
      - url: http://localhost:8000/api/v1test-set-test/
        name: ping
        meta:
          id: req_fa9d46066239419aaff060439a23b4a1
          created: 1742558077569
          modified: 1743776769873
          isPrivate: false
          sortKey: -1742558077569
        method: GET
        headers:
          - name: User-Agent
            value: insomnia/11.0.0
        settings:
          renderRequestBody: true
          encodeUrl: true
          followRedirects: global
          cookies:
            send: true
            store: true
          rebuildPath: true
      - url: http://localhost:8000/api/v1/resources/run/67de87d5b45f09c1072feae6
        name: implement resource
        meta:
          id: req_0721266d77974c7195177d12928b4fa9
          created: 1742636969206
          modified: 1742637382260
          isPrivate: false
          sortKey: -1742636969206
        method: GET
        headers:
          - name: User-Agent
            value: insomnia/11.0.0
        settings:
          renderRequestBody: true
          encodeUrl: true
          followRedirects: global
          cookies:
            send: true
            store: true
          rebuildPath: true
      - url: http://localhost:8000/api/v1/solution
        name: send solution
        meta:
          id: req_f20a29840c8a46a8a284432f3f8317d0
          created: 1742728477518
          modified: 1743359549531
          isPrivate: false
          sortKey: -1742728477518
        method: POST
        body:
          mimeType: multipart/form-data
          params:
            - id: pair_d57426b0aaf642cda6073bd57f1c862c
              name: problem_id
              value: ac43f368-30ff-4831-af65-85d2c1174d00
              disabled: false
              type: text
              multiline: false
            - id: pair_944a543ad7d744bfbcc18fcc924cab04
              name: author_id
              value: ac43f368-30ff-4831-af65-85d2c1174d00
              disabled: false
            - id: pair_979ee580bb9f412e8099abbb8469bf49
              name: file
              disabled: false
              type: file
              fileName: C:\Users\Never\online-contest\FastAPIProject.zip
        headers:
          - name: Content-Type
            value: multipart/form-data
          - name: User-Agent
            value: insomnia/11.0.0
        settings:
          renderRequestBody: true
          encodeUrl: true
          followRedirects: global
          cookies:
            send: true
            store: true
          rebuildPath: true
      - url: http://localhost:8000/api/v1/solution/ping
        name: ping solution
        meta:
          id: req_9bdfb4249d6d4673bd3bc5839ff212ae
          created: 1742733706092
          modified: 1742733731622
          isPrivate: false
          sortKey: -1742733706092
        method: GET
        headers:
          - name: User-Agent
            value: insomnia/11.0.0
        settings:
          renderRequestBody: true
          encodeUrl: true
          followRedirects: global
          cookies:
            send: true
            store: true
          rebuildPath: true
      - url: http://localhost:8000/api/v1/test-set/test/ac43f368-30ff-4831-af65-85d2c1174d00
        name: send test
        meta:
          id: req_62465ad3f6744dee95c973071e254138
          created: 1742926372769
          modified: 1743776801809
          isPrivate: false
          sortKey: -1742926372769
        method: GET
        body:
          mimeType: application/json
          text: |-
            {
            	"problem_id": "ac43f368-30ff-4831-af65-85d2c1174d00",
                "data": [{
            			"request": {
            					"url": "https://api.example.com/login",
            					"method": "POST",
            					"header": {"Content-Type": "application/json"},
            					"body": "{\"username\": \"test\", \"password\": \"pass123\"}"
            			},
            			"response": {
            					"status_code": 200,
            					"header": {"Authorization": "Bearer abc123"},
            					"body": "{\"token\": \"xyz456\"}"
            			}
            		},
            			{
            				"request":{
            					"url": "https://api.example.com/data",
            					"method": "GET",
            					"header": {"Authorization": "Bearer xyz456"},
            					"body": ""
            			},
            			"response":{
            					"status_code": 200,
            					"header": {"Content-Type": "application/json"},
            					"body": "{\"results\": [1, 2, 3]}"
            			}
            		}
            	],
            	"max_response_time" : 200.5,
            	"max_memory_size": 200600
            }
        headers:
          - name: Content-Type
            value: application/json
          - name: User-Agent
            value: insomnia/11.0.0
        settings:
          renderRequestBody: true
          encodeUrl: true
          followRedirects: global
          cookies:
            send: true
            store: true
          rebuildPath: true
      - url: http://localhost:8000/api/v1/test-set/ac43f368-30ff-4831-af65-85d2c1174d00
        name: run test
        meta:
          id: req_e719a8a1230842618b02dbb3b7027ccb
          created: 1743357785610
          modified: 1743789922961
          isPrivate: false
          sortKey: -1743357785610
        method: PUT
        headers:
          - name: User-Agent
            value: insomnia/11.0.0
        settings:
          renderRequestBody: true
          encodeUrl: true
          followRedirects: global
          cookies:
            send: true
            store: true
          rebuildPath: true
  - name: course
    meta:
      id: fld_c5012723c5e9414c90293c7999ad377b
      created: 1743934664704
      modified: 1743934664704
      sortKey: -1743934664704
    children:
      - name: add laboratory
        meta:
          id: req_e0a2ef48f41e49dfbbaa86895f1e691e
          created: 1743934669487
          modified: 1743934700281
          isPrivate: false
          sortKey: -1743934669487
        method: GET
        headers:
          - name: User-Agent
            value: insomnia/11.0.2
        settings:
          renderRequestBody: true
          encodeUrl: true
          followRedirects: global
          cookies:
            send: true
            store: true
          rebuildPath: true
      - url: http://localhost:8081/api/v1/courses/
        name: add course
        meta:
          id: req_e3d005f6a4a743338bb8e6f4d4fa9840
          created: 1743934674119
          modified: 1744123950199
          isPrivate: false
          sortKey: -1743934674119
        method: POST
        body:
          mimeType: application/json
          text: |-
            {
            	"semester": "Fall 2025",
            	"courseName": "web programming",
            	"description": "Introduction backend service"
            }
        headers:
          - name: Content-Type
            value: application/json
            id: pair_61d6a3056f054e239009a208c17ee1a5
          - name: User-Agent
            value: insomnia/11.0.2
            id: pair_10a748bf74d94f5e8431d721eef50712
          - id: pair_a4ba164860424c47a334540801ee9fd0
            name: user-id
            value: ac43f368-30ff-4831-af65-85d2c1174d00
            disabled: false
          - id: pair_7af25da9f30540258fc2189b8134d47c
            name: user-name
            value: ehfgre
            disabled: false
        settings:
          renderRequestBody: true
          encodeUrl: true
          followRedirects: global
          cookies:
            send: true
            store: true
          rebuildPath: true
      - name: get course
        meta:
          id: req_ad27842d2fe24f05844b4be09f8d1bc2
          created: 1743941297634
          modified: 1743941352092
          isPrivate: false
          sortKey: -1743941297634
        method: GET
        headers:
          - name: User-Agent
            value: insomnia/11.0.2
        settings:
          renderRequestBody: true
          encodeUrl: true
          followRedirects: global
          cookies:
            send: true
            store: true
          rebuildPath: true
      - url: http://localhost:8081/api/v1/courses/students/
        name: add student to course
        meta:
          id: req_8cdc594aa9994a7ca46cebd95aa785d1
          created: 1743952686880
          modified: 1744119511611
          isPrivate: false
          sortKey: -1743952686880
        method: PUT
        body:
          mimeType: application/json
          text: |-
            {
            	"studentId" : "85ac609a-d810-4616-b71e-fec5022b4236",
            	"courseId" : "85ac609a-d810-4616-b71e-fec5022b4236"
            }
        headers:
          - name: Content-Type
            value: application/json
          - name: User-Agent
            value: insomnia/11.0.2
        settings:
          renderRequestBody: true
          encodeUrl: true
          followRedirects: global
          cookies:
            send: true
            store: true
          rebuildPath: true
      - url: http://localhost:8080/api/v1/courses/teachers/ac43f368-30ff-4831-af65-85d2c1174d00
        name: get course by teacher id
        meta:
          id: req_3b655b783063451e87bf0502062d2694
          created: 1743952839554
          modified: 1743952879340
          isPrivate: false
          sortKey: -1743952839554
        method: GET
        headers:
          - name: User-Agent
            value: insomnia/11.0.2
        settings:
          renderRequestBody: true
          encodeUrl: true
          followRedirects: global
          cookies:
            send: true
            store: true
          rebuildPath: true
      - url: http://localhost:8081/api/v1/courses/students/85ac609a-d810-4616-b71e-fec5022b4236
        name: get courses by student id
        meta:
          id: req_56e260f878e14a9e9a2573fc8d6da527
          created: 1743953048534
          modified: 1744119524860
          isPrivate: false
          sortKey: -1743953048534
        method: GET
        headers:
          - name: User-Agent
            value: insomnia/11.0.2
        settings:
          renderRequestBody: true
          encodeUrl: true
          followRedirects: global
          cookies:
            send: true
            store: true
          rebuildPath: true
      - url: http://localhost:8080/api/v1/laboratories/
        name: add laboratory
        meta:
          id: req_9969c1e06802480d976794a9fea64f9d
          created: 1743954612653
          modified: 1743954892350
          isPrivate: false
          sortKey: -1743954612653
        method: POST
        body:
          mimeType: application/json
          text: |-
            {
            	"courseId": "85ac609a-d810-4616-b71e-fec5022b4236",
            	"title": "Intrduction to FastAPI",
            	"tags": "Backend",
            	"deadline": "",
            	"description": "sdfd ergret "
            }
        headers:
          - name: Content-Type
            value: application/json
          - name: User-Agent
            value: insomnia/11.0.2
        settings:
          renderRequestBody: true
          encodeUrl: true
          followRedirects: global
          cookies:
            send: true
            store: true
          rebuildPath: true
      - url: http://localhost:8081/api/v1/laboratories/course/85ac609a-d810-4616-b71e-fec5022b4236
        name: get laboratories by course id
        meta:
          id: req_9e1a6d8817e841869b706d73f4265e28
          created: 1743955722748
          modified: 1744119548692
          isPrivate: false
          sortKey: -1743955722748
        method: GET
        headers:
          - name: User-Agent
            value: insomnia/11.0.2
        settings:
          renderRequestBody: true
          encodeUrl: true
          followRedirects: global
          cookies:
            send: true
            store: true
          rebuildPath: true
cookieJar:
  name: Default Jar
  meta:
    id: jar_914c80fa164a06dd5eba1271cca3ade715f8c714
    created: 1741170332926
    modified: 1741170332926
environments:
  name: Base Environment
  meta:
    id: env_914c80fa164a06dd5eba1271cca3ade715f8c714
    created: 1741170332922
    modified: 1741170332922
    isPrivate: false
