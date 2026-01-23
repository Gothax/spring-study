# 링크드 리스트 구현 비교

## 1. 독자적 구현 vs List 인터페이스 구현

### 독자적 구현 (SinglyLinkedList, DoublyLinkedList)

**장점:**
- 자유로운 메서드 설계 (addFirst, addLast 등 직관적인 이름)
- 불필요한 메서드 구현 안해도 됨
- 학습 목적으로 좋음

**단점:**
- Java 표준 컬렉션과 호환 안됨
- for-each 문 사용 불가
- Stream API 사용 불가
- Collections.sort() 같은 유틸리티 사용 불가
- 다른 컬렉션과 상호작용 어려움

**코드 예시:**
```java
SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
list.addLast(10);
list.addFirst(5);
// for-each 불가능!
// Collections.sort(list) 불가능!
```

---

### List 인터페이스 구현 (MyLinkedList)

**장점:**
- Java Collections Framework와 완전 호환
- for-each 문 사용 가능 (Iterable 상속)
- Stream API 사용 가능
- Collections 유틸리티 사용 가능 (sort, shuffle, reverse 등)
- ArrayList, LinkedList 등과 상호 교환 가능
- 다른 컬렉션과 자유롭게 변환 가능

**단점:**
- 구현해야 할 메서드가 많음 (25개 이상)
- 메서드 이름이 Java 표준을 따라야 함
- Iterator, ListIterator도 구현해야 함

**코드 예시:**
```java
List<Integer> list = new MyLinkedList<>();
list.add(10);
list.add(5);

// for-each 가능!
for (Integer num : list) {
    System.out.println(num);
}

// Stream API 가능!
list.stream()
    .filter(n -> n > 5)
    .forEach(System.out::println);

// Collections 유틸리티 가능!
Collections.sort(list);

// ArrayList와 교환 가능!
List<Integer> arrayList = new ArrayList<>(list);
```

---

## 2. 핵심 차이점

| 기능 | 독자적 구현 | List 인터페이스 |
|-----|----------|--------------|
| for-each 문 | ❌ | ✅ |
| Stream API | ❌ | ✅ |
| Collections.sort() | ❌ | ✅ |
| ArrayList와 호환 | ❌ | ✅ |
| 메서드 개수 | 적음 (10개 정도) | 많음 (25개 이상) |
| 학습 난이도 | 쉬움 | 어려움 |
| 실무 사용성 | 낮음 | 높음 |

---

## 3. 실제 사용 예시 비교

### 정렬하기

**독자적 구현:**
```java
SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
list.addLast(5);
list.addLast(2);
list.addLast(8);

// 직접 정렬 알고리즘 구현해야 함!
// Collections.sort() 사용 불가
```

**List 인터페이스:**
```java
List<Integer> list = new MyLinkedList<>();
list.add(5);
list.add(2);
list.add(8);

// 바로 사용 가능!
Collections.sort(list);
```

### 필터링하기

**독자적 구현:**
```java
SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
// Stream 사용 불가, 직접 반복문 작성
SinglyLinkedList<Integer> filtered = new SinglyLinkedList<>();
// 수동으로 필터링 로직 구현...
```

**List 인터페이스:**
```java
List<Integer> list = new MyLinkedList<>();
list.add(1);
list.add(2);
list.add(3);

// Stream으로 간단하게!
List<Integer> even = list.stream()
    .filter(n -> n % 2 == 0)
    .collect(Collectors.toList());
```

---

## 4. 언제 무엇을 사용할까?

### 독자적 구현을 사용할 때:
- 자료구조 학습 목적
- 특수한 요구사항이 있을 때
- 매우 간단한 프로젝트

### List 인터페이스를 사용할 때:
- 실무 프로젝트
- Java 표준 라이브러리와 함께 사용
- Stream API가 필요할 때
- 다른 개발자와 협업할 때
- **대부분의 경우!**

---

## 5. Java에서 제공하는 List 구현체들

Java는 이미 여러 List 구현을 제공합니다:

### ArrayList
- 배열 기반
- 인덱스 접근 빠름 (O(1))
- 중간 삽입/삭제 느림 (O(n))
- 메모리 연속적

### LinkedList
- 노드 기반 (이중 연결 리스트)
- 양 끝 삽입/삭제 빠름 (O(1))
- 인덱스 접근 느림 (O(n))
- 메모리 비연속적

### Vector (레거시)
- ArrayList와 유사하지만 동기화됨
- 잘 사용 안함 (대신 ArrayList 사용)

### 우리의 MyLinkedList
- Java LinkedList와 거의 동일
- 학습 목적으로 직접 구현
- 실제로는 Java의 LinkedList 사용 권장

---

## 결론

**학습용:** 독자적 구현으로 자료구조 이해  
**실무용:** List 인터페이스 구현으로 Java 생태계 활용  
**대부분:** Java가 제공하는 ArrayList, LinkedList 사용
