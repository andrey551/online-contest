import logging
import os
import zipfile

logger = logging.getLogger(__name__)


def extract(zip_path: str, extract_path: str):
    with zipfile.ZipFile(zip_path) as zip:
        zip.extractall(extract_path)


def clean(extract_path: str, name: str):
    try:
        os.remove(extract_path + "/" + name)
        logger.info(f"Removed {extract_path}")
    except OSError as e:
        logger.error(e)
