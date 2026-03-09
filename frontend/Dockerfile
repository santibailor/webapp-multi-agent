# ---- DEPS STAGE ----
FROM node:22-alpine AS deps

WORKDIR /app
COPY frontend/package.json frontend/package-lock.json* ./
RUN npm ci --prefer-offline

# ---- BUILD STAGE ----
FROM node:22-alpine AS builder

WORKDIR /app
COPY --from=deps /app/node_modules ./node_modules
COPY frontend/ .
COPY docs/openapi.yaml ./docs/openapi.yaml

# Generate TypeScript types from OpenAPI spec
RUN npx openapi-typescript docs/openapi.yaml -o src/api/schema.d.ts

ENV NEXT_TELEMETRY_DISABLED=1
RUN npm run build

# ---- RUNTIME STAGE ----
FROM node:22-alpine AS runtime

ENV NODE_ENV=production
ENV NEXT_TELEMETRY_DISABLED=1

# Security: non-root user
RUN addgroup --system --gid 1001 nodejs && \
    adduser  --system --uid 1001 nextjs

WORKDIR /app

COPY --from=builder /app/public ./public

# Leverage Next.js standalone output for minimal image size
COPY --from=builder --chown=nextjs:nodejs /app/.next/standalone ./
COPY --from=builder --chown=nextjs:nodejs /app/.next/static ./.next/static

USER nextjs

EXPOSE 3000
ENV PORT=3000
ENV HOSTNAME="0.0.0.0"

CMD ["node", "server.js"]
