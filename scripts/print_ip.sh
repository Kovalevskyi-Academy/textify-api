#!/usr/bin/env bash
set -Eeuo pipefail

printf "EXTERNAL IP IS: %s\n" "$(tr <ip.txt)"
