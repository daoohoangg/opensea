# Step 1: Build stage
FROM node:22 AS build-stage
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build

# Step 2: Production stage
FROM nginx:alpine AS production-stage
COPY --from=build-stage /app/dist /usr/share/nginx/html
# Ghi đè file cấu hình NGINX nếu cần
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 81
CMD ["nginx", "-g", "daemon off;"]
