### Bean la gi?
- Duoc coi la thanh phan xuong song cua ung dung Spring
- La doi tuong duoc khoi tao, lap rap, quan ly boi Spring IoC container

### Tao ra bean nhu the nao?
- Su dung cac annotation danh dau len class (class level): @Component, @Controller, @RestController, @Service, @Repository, ...
- Su dung annotation @Bean tren method (method level) trong class @Configuration

### Su dung bean nhu the nao?
- Bean thuong duoc su dung trong 1 bean khac (dependency) (A -> B)
 Classroom (A) {
    SinhVien (B)
    GiangVien (B)
    TuVanVien (B)
}
- 3 cach su dung bean:
    + Field-based Injection
    + Constructor-based Injection
    + Setter-based Injection
