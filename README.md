# kubernetes-demo

## 命令

- 在 gateway/consumer/provider 目录下，执行 `mvn clean package` 打包程序。
- `docker build -t <image_name:tag> .` 构建镜像。
- `minikube image load <image_name:tag>` 加载镜像到 minikube。
- 在根目录下执行 `kubectl apply -f <file>` 运行容器。
