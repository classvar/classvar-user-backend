# ClassVAR

나중에 내용이 길어지면 Wiki로 분리하겠습니다.

### GitHub Flow

목적이 분명한 Issue를 생성합니다.

생성된 이슈에 대응되는 작업 브랜치를 생성합니다.

작업 완료 후 Pull Request를 생성하고, Issue와의 관계를 표시합니다. (`fixes #IssueNum`)

PR 생성 시 CI와 코드 리뷰를 통과하면 `main` 브랜치로 병합하고 작업 브랜치는 제거합니다.

### Git Commit Convention

Commit Message는 항상 아래 prefix로 시작합니다.

또한, 각 Commit은 하나의 목적만 수행해야 합니다.

하나의 이슈 안에서 같은 결의 세부 목적만 수행해주세요.

#### Git Commit Prefix

| prefix   | desc                                                         |
| -------- | ------------------------------------------------------------ |
| feat     | 기능 + 테스트를 추가합니다.                                  |
| fix      | 버그 픽스 혹은 기타 오류 수정을 수행합니다.                  |
| docs     | 못 했던 문서화 혹은 최신화를 수행합니다.                     |
| refactor | 국소적인 아키텍처 개선을 목적으로 코드 변경을 수행합니다.    |
| perf     | 성능 개선을 목적으로 코드 변경을 수행합니다.                 |
| test     | 단순히 테스트를 추가하는 게 아니라, 이전에 못 했던 테스트를 추가합니다. |
| style    | 잘못된 코드 포메팅을 고치거나 새로운 코드 포메팅을 시도합니다. |
| chore    | 빌드 프로세스 등의 코드 외적인 변경을 의미합니다.            |

### How to build an image

```bash
$ export TERM=cygwin # windows git bash에서만 사용
$ ./gradlew bootBuildImage --imageName={your_image_name}
```

